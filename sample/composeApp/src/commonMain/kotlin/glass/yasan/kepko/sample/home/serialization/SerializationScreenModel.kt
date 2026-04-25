package glass.yasan.kepko.sample.home.serialization

import glass.yasan.kepko.serialization.contract.ButtonTextContract

internal data class SerializationScreenModel(
    val presets: SerializationPresets,
    val draftJson: String,
    val isError: Boolean,
    val lastValidContract: ButtonTextContract,
    val lastAction: String?,
    val errorMessage: String?,
    val onApplyPreset: (String) -> Unit,
    val onJsonChange: (String) -> Unit,
    val onCaptureAction: (String) -> Unit,
)

