package glass.yasan.kepko.serialization.contract

import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.NamedColor
import glass.yasan.kepko.resource.NamedIcon
import glass.yasan.kepko.serialization.serializer.NamedColorSerializer
import glass.yasan.kepko.serialization.serializer.NamedIconSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalKepkoApi
@Serializable
public data class PreferenceAnnotationContract(
    @SerialName("text")
    public val text: String,
    @SerialName("container_color")
    @Serializable(with = NamedColorSerializer::class)
    public val containerColor: NamedColor? = null,
    @SerialName("content_color")
    @Serializable(with = NamedColorSerializer::class)
    public val contentColor: NamedColor? = null,
    @SerialName("leading_icon")
    @Serializable(with = NamedIconSerializer::class)
    public val leadingIcon: NamedIcon? = null,
    @SerialName("trailing_icon")
    @Serializable(with = NamedIconSerializer::class)
    public val trailingIcon: NamedIcon? = null,
)
