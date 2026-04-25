package glass.yasan.kepko.sample.home.serialization

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextButton
import glass.yasan.kepko.component.TextField
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.serialization.ContractButtonText
import glass.yasan.kepko.serialization.contract.ButtonTextContract
import glass.yasan.kepko.serialization.contract.PreferenceAnnotationContract
import glass.yasan.kepko.serialization.serializer.kepkoJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import glass.yasan.kepko.foundation.color.NamedColor
import glass.yasan.kepko.resource.NamedIcon

@Composable
internal fun SerializationScreen(
    onBackClick: () -> Unit,
) {
    val json = rememberSerializationJson()
    val presets = rememberSerializationPresets(json)
    SerializationScreenContent(
        onBackClick = onBackClick,
        json = json,
        presets = presets,
    )
}

@Composable
private fun rememberSerializationJson(): Json =
    remember {
        Json(from = kepkoJson) {
            prettyPrint = true
        }
    }

@Composable
private fun rememberSerializationPresets(
    json: Json,
): SerializationPresets =
    remember(json) {
        SerializationPresets(
            simpleJson = json.encodeToString(
                ButtonTextContract(
                    onClick = "simple-on-click",
                    text = "Text Value",
                    onClickLabel = "simple-on-click-label",
                    onLongClick = "simple-on-long-click",
                    onLongClickLabel = "simple-on-long-click-label",
                    onDoubleClick = "simple-on-double-click",
                ),
            ),
            withAnnotationJson = json.encodeToString(
                ButtonTextContract(
                    onClick = "annotation-on-click",
                    text = "Text Value",
                    onClickLabel = "annotation-on-click-label",
                    onLongClick = "annotation-on-long-click",
                    onLongClickLabel = "annotation-on-long-click-label",
                    onDoubleClick = "annotation-on-double-click",
                    annotation = PreferenceAnnotationContract(
                        text = "new",
                        containerColor = NamedColor.INFORMATION,
                    ),
                ),
            ),
            completeJson = json.encodeToString(
                ButtonTextContract(
                    onClick = "on-click",
                    text = "Text Value",
                    onClickLabel = "on-click-label",
                    onLongClick = "on-long-click",
                    onLongClickLabel = "on-long-click-label",
                    onDoubleClick = "on-double-click",
                    containerColor = NamedColor.INFORMATION,
                    contentColor = NamedColor.ON_INFORMATION,
                    leadingIcon = NamedIcon.ERROR,
                    trailingIcon = NamedIcon.ERROR,
                    enabled = true,
                    fillWidth = false,
                    annotation = PreferenceAnnotationContract(
                        text = "beta",
                        containerColor = NamedColor.CONTENT,
                        contentColor = NamedColor.FOREGROUND,
                        leadingIcon = NamedIcon.WARNING,
                        trailingIcon = NamedIcon.WARNING,
                    ),
                ),
            ),
        )
    }

@Composable
private fun SerializationScreenContent(
    onBackClick: () -> Unit,
    json: Json,
    presets: SerializationPresets,
) {
    var draftJson by rememberSaveable { mutableStateOf(presets.simpleJson) }
    var lastValidContract by remember {
        mutableStateOf(json.decodeFromString<ButtonTextContract>(presets.simpleJson))
    }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }
    var lastAction by rememberSaveable { mutableStateOf<String?>(null) }

    fun applyPreset(presetJson: String) {
        draftJson = presetJson
        lastValidContract = json.decodeFromString(presetJson)
        errorMessage = null
        lastAction = null
    }

    Scaffold(
        title = "Serialization",
        onBackClick = onBackClick,
    ) { contentPadding ->
        SerializationScreenBody(
            model = SerializationScreenModel(
                presets = presets,
                draftJson = draftJson,
                isError = errorMessage != null,
                lastValidContract = lastValidContract,
                lastAction = lastAction,
                errorMessage = errorMessage,
                onApplyPreset = ::applyPreset,
                onJsonChange = { next ->
                    draftJson = next
                    runCatching { json.decodeFromString<ButtonTextContract>(next) }
                        .onSuccess { decoded ->
                            lastValidContract = decoded
                            errorMessage = null
                        }
                        .onFailure { throwable ->
                            errorMessage = throwable.message ?: throwable::class.simpleName
                        }
                },
                onCaptureAction = { action -> lastAction = action },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(horizontal = 12.dp)
                .padding(top = 16.dp),
        )
    }
}

@Composable
private fun SerializationScreenBody(
    model: SerializationScreenModel,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier,
    ) {
        PresetsSection(
            presets = model.presets,
            onApplyPreset = model.onApplyPreset,
        )

        JsonSection(
            value = model.draftJson,
            isError = model.isError,
            onValueChange = model.onJsonChange,
        )

        ComposableSection(
            contract = model.lastValidContract,
            lastAction = model.lastAction,
            errorMessage = model.errorMessage,
            onCaptureAction = model.onCaptureAction,
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun PresetsSection(
    presets: SerializationPresets,
    onApplyPreset: (String) -> Unit,
) {
    SectionTitle(
        text = "Presets",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 2.dp),
    )

    PresetButtonsRow(
        onSimpleClick = { onApplyPreset(presets.simpleJson) },
        onWithAnnotationClick = { onApplyPreset(presets.withAnnotationJson) },
        onCompleteClick = { onApplyPreset(presets.completeJson) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    )
}

@Composable
private fun JsonSection(
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    SectionTitle(
        text = "JSON",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 2.dp),
    )

    JsonEditorField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
    )
}

@Composable
private fun ComposableSection(
    contract: ButtonTextContract,
    lastAction: String?,
    errorMessage: String?,
    onCaptureAction: (String) -> Unit,
) {
    SectionTitle(
        text = "Composable",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp),
    )

    ContractButtonText(
        contract = contract,
        onClick = { action -> onCaptureAction("on_click: $action") },
        onLongClick = { action -> onCaptureAction("on_long_click: $action") },
        onDoubleClick = { action -> onCaptureAction("on_double_click: $action") },
    )

    lastAction?.let { action ->
        CapturedActionText(
            text = action,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp),
        )
    }

    errorMessage?.let { message ->
        ErrorMessageText(
            message = message,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp),
        )
    }
}

@Composable
private fun PresetButtonsRow(
    onSimpleClick: () -> Unit,
    onWithAnnotationClick: () -> Unit,
    onCompleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val padding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        TextButton(
            onClick = onSimpleClick,
            content = { Text(text = "Simple", maxLines = 1) },
            contentPadding = padding,
            modifier = Modifier.weight(1f),
        )

        TextButton(
            onClick = onWithAnnotationClick,
            content = { Text(text = "With annotation", maxLines = 1) },
            contentPadding = padding,
            modifier = Modifier.weight(1f),
        )

        TextButton(
            onClick = onCompleteClick,
            content = { Text(text = "Complete", maxLines = 1) },
            contentPadding = padding,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
        modifier = modifier,
    )
}

@Composable
private fun JsonEditorField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = null,
        singleLine = false,
        minLines = 6,
        maxLines = 18,
        textStyle = TextStyle(fontSize = 12.sp, lineHeight = 14.sp),
        isError = isError,
        modifier = modifier,
    )
}

@Composable
private fun CapturedActionText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = TextStyle(fontSize = 10.sp, lineHeight = 12.sp),
        color = KepkoTheme.colors.contentSubtle,
        modifier = modifier,
    )
}

@Composable
private fun ErrorMessageText(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message,
        style = TextStyle(fontSize = 10.sp, lineHeight = 12.sp),
        color = KepkoTheme.colors.danger,
        modifier = modifier,
    )
}
