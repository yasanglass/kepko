package glass.yasan.kepko.serialization.contract

import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalKepkoApi
@Serializable
public data class KepkoThemeContract(
    @EncodeDefault
    @SerialName("schema_version")
    public val schemaVersion: Int = CURRENT_SCHEMA_VERSION,
    @SerialName("palette_id")
    public val paletteId: String,
    @SerialName("grayscale")
    public val grayscale: Boolean,
    @SerialName("outline_dp")
    public val outlineDp: Float,
    @SerialName("roundness")
    public val roundness: Float,
) {
    public companion object {
        public const val CURRENT_SCHEMA_VERSION: Int = 1
    }
}
