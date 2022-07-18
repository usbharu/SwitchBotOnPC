package io.github.usbharu.switchbot_on_pc.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.usbharu.switchbot_on_pc.model.*
import io.github.usbharu.switchbot_on_pc.util.HttpURLConnectionUtil
import java.net.URL

open class SwitchBot private constructor(private val token: String) {
 var debug:Boolean = false

    constructor(token: String, debug: Boolean) : this(token) {
        this.debug = debug;
    }

    fun devices(): GetDevicesListResponseBody {
        if (debug) {
            return GetDevicesListResponseBody(
                listOf(
                    Device(
                        "1234567890",
                        "device0",
                        DeviceType.HubMini,
                        true,
                        "1234567890",
                        token
                    )
                ),
                listOf(
                    RemoteDevice(
                        "0987654321",
                        "remoteDevice0",
                        RemoteType.Fan,
                        "1234567890",
                        token
                    ),
                    RemoteDevice(
                        "1111111111",
                        "remoteDevice1",
                        RemoteType.Light,
                        "1234567890",
                        token
                    ),
                    RemoteDevice(
                        "1111111112",
                        "remoteDevice2",
                        RemoteType.Light,
                        "1234567890",
                        token
                    ),
                    RemoteDevice(
                        "1111111112",
                        "remoteDevice3",
                        RemoteType.Light,
                        "1234567890",
                        token
                    ),
                    RemoteDevice(
                        "1111111113",
                        "remoteDevice4",
                        RemoteType.Light,
                        "1234567890",
                        token
                    )
                )
            )
        }
        val request =
            HttpURLConnectionUtil.getRequest(URL("https://api.switch-bot.com/v1.0/devices"), token)
        val readValue = MAPPER.readValue<GetDevicesListResponse>(request)
        if (readValue.body == null) {
            throw IllegalStateException("bodyがnullです")
        }
        readValue.body.deviceList.forEach { device ->
            device.setToken(token);
        }
        for (remoteDevice in readValue.body.infraredRemoteList) {
            remoteDevice.setToken(token)
        }

        return readValue.body
    }

    fun status(deviceId: String): GetDeviceStatusResponseBody {
        if (debug){
            return GetDeviceStatusResponseBody(deviceId,DeviceType.HubMini,deviceId)
        }
        val request = HttpURLConnectionUtil.getRequest(
            URL("https://api.switch-bot.com/v1.0/devices/$deviceId/status"), token
        )
        val readValue = MAPPER.readValue<GetDeviceStatusResponse>(request)
        if (readValue.body == null) {
            throw IllegalStateException("bodyがnullです")
        }
        return readValue.body
    }

    fun commands(deviceId: String, parameter: String): SendCommandsResponseBody {
        if (debug){
            return SendCommandsResponseBody()
        }
        val request = HttpURLConnectionUtil.postRequest(
            URL("https://api.switch-bot.com/v1.0/devices/$deviceId/commands"),
            token, parameter
        )
        val readValue = MAPPER.readValue<SendCommandsResponse>(request)
        if (readValue.statusCode != "100") {
            throw IllegalStateException("コマンドの実行に失敗しました ：${readValue.statusCode}")
        }
        return readValue.body
    }

    companion object {
        private val instanceMap: MutableMap<String, SwitchBot> = HashMap()
        private val MAPPER = jacksonObjectMapper()

        @Synchronized
        fun create(token: String,debug:Boolean = false): SwitchBot {
            return if (instanceMap.containsKey(token)) {
                instanceMap[token]!!
            } else {
                val instance = SwitchBot(token,debug)
                instanceMap[token] = instance
                instance
            }
        }
    }
}
