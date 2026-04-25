package glass.yasan.kepko.serialization.serializer

import glass.yasan.kepko.resource.NamedIcon
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializes a [NamedIcon] as its stable [NamedIcon.id] string.
 *
 * Unknown ids deserialize to null.
 */
@OptIn(ExperimentalSerializationApi::class)
internal object NamedIconSerializer : KSerializer<NamedIcon?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            serialName = "glass.yasan.kepko.serialization.serializer.NamedIconId",
            kind = PrimitiveKind.STRING,
        )

    override fun serialize(encoder: Encoder, value: NamedIcon?) {
        if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeString(value.id)
        }
    }

    override fun deserialize(decoder: Decoder): NamedIcon? {
        if (!decoder.decodeNotNullMark()) return decoder.decodeNull()
        return NamedIcon.fromIdOrNull(decoder.decodeString())
    }
}
