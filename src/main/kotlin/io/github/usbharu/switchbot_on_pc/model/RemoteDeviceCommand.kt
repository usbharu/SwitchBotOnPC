package io.github.usbharu.switchbot_on_pc.model

enum class RemoteDeviceCommand(
    val command: String,
    commandParameterType: RemoteDeviceCommandParameterType = RemoteDeviceCommandParameterType.DEFAULT
) : ICommand {
    TURN_OFF("turnOff"),
    TURN_ON("turnOn"),
    SET_ALL("setAll",RemoteDeviceCommandParameterType.AIR_CONDITIONER),
    SET_CHANNEL("SetChannel",RemoteDeviceCommandParameterType.CHANNEL_NUMBER),
    VOLUME_ADD("volumeAdd"),
    VOLUME_SUB("volumeSub"),
    CHANNEL_ADD("channelAdd"),
    CHANNEL_SUB("channelSub"),
    SET_MUTE("setMute"),
    FAST_FORWARD("FastForward"),
    REWIND("Rewind"),
    NEXT("Next"),
    PREVIOUS("Previous"),
    PAUSE("Pause"),
    PLAY("Play"),
    STOP("Stop"),
    SWING("swing"),
    TIMER("timer"),
    LOW_SPEED("lowSpeed"),
    MIDDLE_SPEED("middleSpeed"),
    HIGH_SPEED("highSpeed"),
    BRIGHTNESS_UP("brightnessUp"),
    BRIGHTNESS_DOWN("brightnessDown")
}
