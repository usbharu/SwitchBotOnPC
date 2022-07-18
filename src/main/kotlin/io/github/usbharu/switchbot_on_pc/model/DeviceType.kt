package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import java.util.*

enum class DeviceType(val deviceTypeName: String,val deviceCommand: Array<DeviceCommand> = arrayOf(DeviceCommand.TURN_ON,DeviceCommand.TURN_OFF)) : JsonSerializable {
    Hub("Hub", arrayOf()),
    HubPlus("Hub Plus", arrayOf()),
    HubMini("Hub Mini", arrayOf()),
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
