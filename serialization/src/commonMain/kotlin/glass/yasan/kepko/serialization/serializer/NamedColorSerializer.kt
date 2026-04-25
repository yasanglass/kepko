package glass.yasan.kepko.serialization.serializer

import glass.yasan.kepko.foundation.color.NamedColor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializes a [NamedColor] as its stable [NamedColor.id] string.
 *
 * Unknown ids deserialize to null.
 */
@OptIn(ExperimentalSerializationApi::class)
internal object NamedColorSerializer : KSerializer<NamedColor?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            serialName = "glass.yasan.kepko.serialization.serializer.NamedColorId",
            kind = PrimitiveKind.STRING,
        )

    override fun serialize(encoder: Encoder, value: NamedColor?) {
        if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeString(value.id)
        }
    }

    override fun deserialize(decoder: Decoder): NamedColor? {
        if (!decoder.decodeNotNullMark()) return decoder.decodeNull()
        return NamedColor.fromIdOrNull(decoder.decodeString())
    }
}
