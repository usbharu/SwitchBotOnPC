package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonIgnoreProperties(ignoreUnknown = true)
class GetDeviceStatusResponseBody(val deviceId:String?, @param:JsonDeserialize(using = DeviceType.DeviceTypeDeserializer::class) val deviceType:DeviceType?, val hubDeviceId:String?)
