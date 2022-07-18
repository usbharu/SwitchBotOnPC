package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.github.usbharu.switchbot_on_pc.api.SwitchBot

data class RemoteDevice(
    val deviceId: String,
    val deviceName: String,
    @param:JsonDeserialize(using = RemoteType.RemoteTypeDeserializer::class) val remoteType: RemoteType,
    val hubDeviceId: String,
    private var token: String? = null
) :IDevice {


    fun setToken(token: String?) {
        this.token = token
    }

    private fun getToken(): String? {
        return token
    }

    override fun status(): GetDeviceStatusResponseBody{
        return SwitchBot.create(token!!).status(deviceId)
    }

    override fun commands(parameter: String): SendCommandsResponseBody{
        println(parameter)
        return SwitchBot.create(token!!).commands(deviceId,parameter)
    }

    override fun commands(command: ICommand, parameter: String){
        if (command is RemoteDeviceCommand&&remoteType.remoteDeviceCommand.contains(command)) {
            val sb = StringBuilder()
            commands(sb.append("{\"command\": \"").append(command.command).append("\",\"parameter\": \"")
                .append(parameter).append("\",\"commandType\": \"command\"}").toString())
            return
        }
        throw IllegalArgumentException("実行できない種類のコマンドです $command")
    }
}
