package glass.yasan.kepko.serialization.contract

import glass.yasan.kepko.serialization.serializer.kepkoJson
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerializationException

internal class KepkoThemeContractTest {

    @Test
    fun givenTheme_whenConvertedToJsonAndBack_thenRoundTripMatches() {
        val contract = KepkoThemeContract(
            paletteId = "catppuccin-mocha",
            grayscale = true,
            outlineDp = 2f,
            roundness = 0.75f,
        )

        val encoded = kepkoJson.encodeToString(contract)
        val decoded = kepkoJson.decodeFromString<KepkoThemeContract>(encoded)

        assertContains(encoded, "\"schema_version\":${KepkoThemeContract.CURRENT_SCHEMA_VERSION}")
        assertContains(encoded, "\"palette_id\":\"catppuccin-mocha\"")
        assertContains(encoded, "\"outline_dp\":")
        assertEquals(contract, decoded)
    }

    @Test
    fun givenPayloadWithUnknownField_whenDecoded_thenFieldIsIgnored() {
        val encoded = kepkoJson.encodeToString(createContract())
        val payloadWithFutureField = encoded.dropLast(1) + ",\"future_value\":true}"

        val decoded = kepkoJson.decodeFromString<KepkoThemeContract>(payloadWithFutureField)

        assertEquals(createContract(), decoded)
    }

    @Test
    fun givenPayloadWithoutSchemaVersion_whenDecoded_thenCurrentVersionIsUsed() {
        val payload = kepkoJson.encodeToString(createContract()).replace("\"schema_version\":1,", "")

        val decoded = kepkoJson.decodeFromString<KepkoThemeContract>(payload)

        assertEquals(KepkoThemeContract.CURRENT_SCHEMA_VERSION, decoded.schemaVersion)
    }

    @Test
    fun givenMalformedPayload_whenDecoded_thenSerializationExceptionIsThrown() {
        assertFailsWith<SerializationException> {
            kepkoJson.decodeFromString<KepkoThemeContract>("not-json")
        }
    }

    private fun createContract(): KepkoThemeContract = KepkoThemeContract(
        paletteId = "dark",
        grayscale = false,
        outlineDp = 1f,
        roundness = 1f,
    )
}
