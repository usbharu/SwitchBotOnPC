package io.github.usbharu.switchbot_on_pc.util

import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL

object HttpURLConnectionUtil {

    fun getRequest(url: URL, token: String?): String {
        println("Get Request url:$url token:$token")
        val urlConnection = url.openConnection() as HttpURLConnection
        if (token != null) {
            urlConnection.setRequestProperty("Authorization", token)
        }
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf8")
        urlConnection.requestMethod = "GET"
        urlConnection.connect()
        return urlConnection.inputStream.bufferedReader().use { it.readText() }
    }

    fun postRequest(url: URL,token: String?,parameter:String):String{
        println("Post Request url:$url parameter:$parameter")
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "POST"
        urlConnection.doInput = true
        urlConnection.doOutput = true
        urlConnection.setRequestProperty("Authorization",token)
        urlConnection.setRequestProperty("Content-Type","application/json; charset=utf8")

        urlConnection.connect()

        val ps = PrintStream(urlConnection.outputStream)
        ps.print(parameter)
        ps.close()

        return urlConnection.inputStream.bufferedReader().use { it.readText() }
    }
}
