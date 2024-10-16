package com.rohitjakhar.ratingdialog.compose.buttons

import java.io.Serializable

fun interface RateDialogClickListener : Serializable {
    fun onClick()
}

fun interface ConfirmButtonClickListener : Serializable {
    fun onClick(userRating: Float)
}

fun interface CustomFeedbackButtonClickListener : Serializable {
    fun onClick(userFeedbackText: String)
}
