package io.github.usbharu.switchbot_on_pc.util
class GetResource {
    fun kotlinGetResourceAtStream(fileName :String) = this.javaClass.classLoader.getResourceAsStream(fileName)
}
