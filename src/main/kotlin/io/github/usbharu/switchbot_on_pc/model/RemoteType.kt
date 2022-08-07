package io.github.usbharu.switchbot_on_pc.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import io.github.usbharu.switchbot_on_pc.model.RemoteDeviceCommand.*
import java.util.*

enum class RemoteType(
    val remoteDeviceTypeName: String, val remoteDeviceCommand: Array<RemoteDeviceCommand> = arrayOf(
        TURN_ON, TURN_OFF
    )
) : JsonSerializable {
    AirConditioner("Air Conditioner", arrayOf(TURN_ON, TURN_OFF, SET_ALL)), TV(
        "TV",
        arrayOf(TURN_ON, TURN_OFF, SET_CHANNEL, VOLUME_ADD, VOLUME_SUB, CHANNEL_ADD, CHANNEL_SUB)
    ),
    Light("Light", arrayOf(BRIGHTNESS_UP, BRIGHTNESS_DOWN)), Fan(
        "Fan", arrayOf(SWING, TIMER, LOW_SPEED, MIDDLE_SPEED, HIGH_SPEED)
    ),
    IPTVStreamer(
        "IPTV/Streamer",
        arrayOf(TURN_ON, TURN_OFF, SET_CHANNEL, VOLUME_ADD, VOLUME_SUB, CHANNEL_ADD, CHANNEL_SUB)
    ),
    SetTopBox(
        "Set Top Box",
        arrayOf(TURN_ON, TURN_OFF, SET_CHANNEL, VOLUME_ADD, VOLUME_SUB, CHANNEL_ADD, CHANNEL_SUB)
    ),
    DVD(
        "DVD", arrayOf(
            TURN_ON, TURN_OFF, SET_MUTE, FAST_FORWARD, REWIND, NEXT, PREVIOUS, PAUSE, PLAY, STOP
        )
    ),
    Projector("Projector"), Camera("Camera"), AirPurifier("Air Purifier"), Speaker(
        "Speaker", arrayOf(TURN_ON, TURN_OFF, VOLUME_ADD, VOLUME_SUB)
    ),
    WaterHeater(
        "WaterHeater"
    ),
    VacuumCleaner("Vacuum Cleaner"), Others("Others");

    override fun serialize(gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(remoteDeviceTypeName)
    }

    override fun serializeWithType(
        gen: JsonGenerator?, serializers: SerializerProvider?, typeSer: TypeSerializer?
    ) {
        gen?.writeString(remoteDeviceTypeName)
    }

    class RemoteTypeDeserializer : JsonDeserializer<RemoteType>() {
        override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): RemoteType {
            println(p)
            return RemoteType.getByName(p!!.valueAsString)
        }
    }

    companion object {
        fun getByName(name: String): RemoteType {
            println(name)
            return Arrays.stream(RemoteType.values())
                .filter { type -> name.endsWith(type.remoteDeviceTypeName) }.findFirst()
                .orElseThrow()
        }
    }
}
