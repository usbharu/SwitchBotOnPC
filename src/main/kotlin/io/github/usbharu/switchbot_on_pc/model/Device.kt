package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.github.usbharu.switchbot_on_pc.api.SwitchBot
import java.lang.StringBuilder

data class Device(
    val deviceId: String?,
    val deviceName: String,
    @param:JsonDeserialize(using = DeviceType.DeviceTypeDeserializer::class) val deviceType: DeviceType,
    val enableCloudService: Boolean,
    val hubDeviceId: String?,
    private var token: String? = null
) :IDevice {

    fun setToken(token: String?) {
        this.token = token
    }

    private fun getToken(): String? {
        return token
    }

    override fun status(): GetDeviceStatusResponseBody {
        return SwitchBot.create(token!!).status(deviceId!!)
    }

    override fun commands(parameter: String): SendCommandsResponseBody {
        return SwitchBot.create(token!!).commands(deviceId!!, parameter)
    }


    override fun commands(command: ICommand, parameter: String) {
        if (command is DeviceCommand&& deviceType.deviceCommand.contains(command)) {
            val sb = StringBuilder()
            commands(
                sb.append("{\"command\": \"").append(command.name).append("\",\"parameter\": \"")
                    .append(parameter).append("\",\"commandType\": \"command\"}").toString()
            )
            return
        }
        throw IllegalArgumentException("実行できない種類のコマンドです $command")
    }
}
