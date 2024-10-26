package com.rohitjakhar.core.buttons

import androidx.annotation.StringRes
import java.io.Serializable

class RateButton(@StringRes var textId: Int, var text: String?, @Transient var rateDialogClickListener: RateDialogClickListener?) : Serializable

class ConfirmButton(@StringRes var textId: Int, var text: String?, @Transient var confirmButtonClickListener: ConfirmButtonClickListener?) : Serializable

class CustomFeedbackButton(
    @StringRes var textId: Int,
    var text: String?,
    @Transient var customFeedbackButtonClickListener: CustomFeedbackButtonClickListener?,
) : Serializable
