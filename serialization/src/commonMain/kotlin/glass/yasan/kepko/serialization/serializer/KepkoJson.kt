package glass.yasan.kepko.serialization.serializer

import kotlinx.serialization.json.Json

/**
 * Default [Json] configuration for Kepko serialization contracts.
 */
public val kepkoJson: Json = Json {
    ignoreUnknownKeys = true
}
