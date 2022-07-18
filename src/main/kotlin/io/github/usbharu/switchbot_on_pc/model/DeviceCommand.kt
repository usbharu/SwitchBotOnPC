package io.github.usbharu.switchbot_on_pc.model

enum class DeviceCommand(val command:String,commandParameterType: DeviceCommandParameterType = DeviceCommandParameterType.DEFAULT) : ICommand{
    TURN_OFF("turnOff",DeviceCommandParameterType.DEFAULT),
    TURN_ON("turnOn",DeviceCommandParameterType.DEFAULT),
    PRESS("press",DeviceCommandParameterType.DEFAULT),
    SET_POSITION("setPosition",DeviceCommandParameterType.CURTAIN),
    SET_MODE("setMode",DeviceCommandParameterType.HUMIDIFIER),
    SET_ALL_STATUS("setAllStatus",DeviceCommandParameterType.SMART_FAN),
    TOGGLE("toggle",DeviceCommandParameterType.DEFAULT),
    SET_BRIGHTNESS("setBrightness",DeviceCommandParameterType.ONE_TO_ONE_HUNDRED),
    SET_COLOR("setColor",DeviceCommandParameterType.COLOR),
    SET_COLOR_TEMPERATURE("setColorTemperature",DeviceCommandParameterType.COLOR_TEMPERATURE),
}
