package glass.yasan.kepko.serialization.contract

import glass.yasan.kepko.foundation.color.NamedColor
import glass.yasan.kepko.resource.NamedIcon
import glass.yasan.kepko.serialization.serializer.kepkoJson
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class ButtonContractTest {

    private val json = kepkoJson

    @Test
    fun givenConfig_whenSerializedAndDeserialized_thenRoundTripMatches() {
        val contract = ButtonContract(
            onClick = "settings/open",
            text = "Button Text",
            onClickLabel = "Open settings",
            onLongClick = "settings/long-press",
            onLongClickLabel = "Open settings menu",
            onDoubleClick = "settings/double-click",
            containerColor = NamedColor.CAUTION,
            contentColor = NamedColor.ON_CAUTION,
            leadingIcon = NamedIcon.LOCK,
            trailingIcon = NamedIcon.CHEVRON_FORWARD,
            enabled = false,
            fillWidth = false,
            annotation = PreferenceAnnotationContract(
                text = "NEW",
                containerColor = NamedColor.INFORMATION,
                contentColor = null,
                leadingIcon = null,
                trailingIcon = NamedIcon.INFO,
            ),
        )

        val encoded = json.encodeToString(contract)
        val decoded = json.decodeFromString<ButtonContract>(encoded)

        assertContains(encoded, "\"on_click\":\"settings/open\"")
        assertContains(encoded, "\"leading_icon\":\"${NamedIcon.LOCK.id}\"")
        assertContains(encoded, "\"trailing_icon\":\"${NamedIcon.CHEVRON_FORWARD.id}\"")
        assertContains(encoded, "\"container_color\":\"${NamedColor.CAUTION.id}\"")
        assertContains(encoded, "\"content_color\":\"${NamedColor.ON_CAUTION.id}\"")
        assertContains(encoded, "\"annotation\":{")
        assertEquals(contract, decoded)
    }

    @Test
    fun givenDefaultConfig_whenCreated_thenEnabledDefaultsToTrue() {
        assertEquals(true, ButtonContract(onClick = "id").enabled)
    }
}
