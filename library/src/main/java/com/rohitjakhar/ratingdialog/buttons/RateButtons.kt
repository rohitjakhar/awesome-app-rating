package com.rohitjakhar.ratingdialog.buttons

import androidx.annotation.StringRes
import java.io.Serializable

internal class RateButton(@StringRes var textId: Int, var text: String?, @Transient var rateDialogClickListener: RateDialogClickListener?) : Serializable

internal class ConfirmButton(@StringRes var textId: Int, var text: String?, @Transient var confirmButtonClickListener: ConfirmButtonClickListener?) : Serializable

internal class CustomFeedbackButton(
    @StringRes var textId: Int,
    var text: String?,
    @Transient var customFeedbackButtonClickListener: CustomFeedbackButtonClickListener?,
) : Serializable
