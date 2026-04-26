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
public data class ButtonContract(
    @SerialName("on_click")
    public val onClick: String,
    @SerialName("text")
    public val text: String? = null,
    @SerialName("on_click_label")
    public val onClickLabel: String? = null,
    @SerialName("on_long_click")
    public val onLongClick: String? = null,
    @SerialName("on_long_click_label")
    public val onLongClickLabel: String? = null,
    @SerialName("on_double_click")
    public val onDoubleClick: String? = null,
    @SerialName("container_color")
    @Serializable(with = NamedColorSerializer::class)
    public val containerColor: NamedColor? = NamedColor.FOREGROUND,
    @SerialName("content_color")
    @Serializable(with = NamedColorSerializer::class)
    public val contentColor: NamedColor? = null,
    @SerialName("leading_icon")
    @Serializable(with = NamedIconSerializer::class)
    public val leadingIcon: NamedIcon? = null,
    @SerialName("trailing_icon")
    @Serializable(with = NamedIconSerializer::class)
    public val trailingIcon: NamedIcon? = null,
    @SerialName("enabled")
    public val enabled: Boolean = true,
    @SerialName("fill_width")
    public val fillWidth: Boolean = true,
    @SerialName("badge")
    public val badge: PreferenceAnnotationContract? = null,
)
