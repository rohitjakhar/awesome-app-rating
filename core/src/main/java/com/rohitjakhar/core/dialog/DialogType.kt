package com.rohitjakhar.core.dialog

import java.io.Serializable

enum class DialogType : Serializable {
    RATING_OVERVIEW, RATING_STORE, FEEDBACK_MAIL, FEEDBACK_CUSTOM
}

enum class ReviewType: Serializable {
    POPUP, BOTTOM_SHEET
}
