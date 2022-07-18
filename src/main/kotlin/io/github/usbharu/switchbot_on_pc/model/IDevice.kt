package io.github.usbharu.switchbot_on_pc.model

interface IDevice {
    fun status(): GetDeviceStatusResponseBody
    fun commands(parameter: String): SendCommandsResponseBody
    fun commands(command : ICommand , parameter: String = "default")
}
