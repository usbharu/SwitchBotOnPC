package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import io.github.usbharu.switchbot_on_pc.model.DeviceCommand.*
import java.util.*

enum class DeviceType(val deviceTypeName: String,val deviceCommand: Array<DeviceCommand> = arrayOf(
    TURN_ON,
    TURN_OFF
)) : JsonSerializable {
    Bot("Bot"),
    Hub("Hub", arrayOf()),
    HubPlus("Hub Plus", arrayOf()),
    HubMini("Hub Mini", arrayOf()),
    Plug("Plug"),
    Curtain("Curtain",arrayOf(TURN_OFF,SET_POSITION)),
    Humidifier("Humidifier", arrayOf(SET_MODE)),
    SmartFan("SmartFan", arrayOf(TURN_ON,TURN_OFF,SET_ALL_STATUS)),
    ColorBulb("ColorBulb", arrayOf(TURN_ON,TURN_OFF,TOGGLE,SET_BRIGHTNESS,SET_COLOR,SET_COLOR_TEMPERATURE)),
    StripLight("StripLight", arrayOf(TURN_ON,TURN_OFF,TOGGLE,SET_BRIGHTNESS,SET_COLOR)),
    PlugMiniUS("Plug Mini (US)", arrayOf(TURN_ON,TURN_OFF,TOGGLE)),
    PlugMiniJP("Plug Mini (JP)", arrayOf(TURN_ON,TURN_OFF,TOGGLE)),
    Lock("Lock", arrayOf()),
    MeterPlusJP("Meter Plus (JP)", arrayOf()),
    MeterPlusUS("Meter Plus (US)", arrayOf()),
    unknown("");

    override fun serialize(gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(deviceTypeName)
    }

    override fun serializeWithType(
        gen: JsonGenerator?,
        serializers: SerializerProvider?,
        typeSer: TypeSerializer?
    ) {
        gen?.writeString(deviceTypeName)
    }

    class DeviceTypeDeserializer : JsonDeserializer<DeviceType>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DeviceType {
            return DeviceType.getByName(p!!.valueAsString)
        }

    }

    companion object {
        fun getByName(name:String):DeviceType{
            return Arrays.stream(DeviceType.values()).filter { type -> type.deviceTypeName == name }.findFirst()
                .orElse(unknown)

        }
    }
}
