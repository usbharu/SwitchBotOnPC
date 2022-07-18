package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import java.util.*

enum class RemoteType(val remoteDeviceTypeName: String,val remoteDeviceCommand: Array<RemoteDeviceCommand> = arrayOf(RemoteDeviceCommand.TURN_ON,RemoteDeviceCommand.TURN_OFF)) : JsonSerializable {
    AirConditioner("Air Conditioner"),
    TV("TV"),
    Light("Light"),
    Fan("DIY Fan");

    override fun serialize(gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(remoteDeviceTypeName)
    }

    override fun serializeWithType(
        gen: JsonGenerator?,
        serializers: SerializerProvider?,
        typeSer: TypeSerializer?
    ) {
        gen?.writeString(remoteDeviceTypeName)
    }

    class RemoteTypeDeserializer : JsonDeserializer<RemoteType>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): RemoteType {
            return RemoteType.getByName(p!!.valueAsString)
        }
    }

    companion object {
        fun getByName(name: String): RemoteType {
            println(name)
            return Arrays.stream(RemoteType.values())
                .filter { type -> type.remoteDeviceTypeName == name }.findFirst().orElseThrow()
        }
    }
}
