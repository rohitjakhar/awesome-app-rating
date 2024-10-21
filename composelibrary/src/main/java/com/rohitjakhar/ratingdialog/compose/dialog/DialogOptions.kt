package com.rohitjakhar.ratingdialog.compose.dialog

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import com.rohitjakhar.ratingdialog.compose.R
import com.rohitjakhar.ratingdialog.compose.buttons.ConfirmButton
import com.rohitjakhar.ratingdialog.compose.buttons.CustomFeedbackButton
import com.rohitjakhar.ratingdialog.compose.buttons.RateButton
import com.rohitjakhar.ratingdialog.compose.buttons.RateDialogClickListener
import com.rohitjakhar.ratingdialog.compose.preferences.MailSettings
import com.rohitjakhar.ratingdialog.compose.preferences.RatingThreshold
import java.io.Serializable

internal class DialogOptions : Serializable {

    @Transient
    var iconDrawable: Drawable? = null
    var iconUri: String? = null
    var customTheme: Int = 0
    var rateLaterButton: RateButton = RateButton(R.string.rating_dialog_button_rate_later, null, null)
    var rateNeverButton: RateButton? = null
    var ratingThreshold: RatingThreshold = RatingThreshold.THREE
    var customCondition: (() -> Boolean)? = null
    var customConditionToShowAgain: (() -> Boolean)? = null
    var countAppLaunch: Boolean = true
    var countOfLaterButtonClicksToShowNeverButton = 0

    /**
     * rating dialog overview
     */

    @StringRes
    var titleTextId = R.string.rating_dialog_overview_title
    var titleText: String? = null

    @StringRes
    var messageTextId: Int? = null
    var messageText: String? = null
    var confirmButton = ConfirmButton(R.string.rating_dialog_overview_button_confirm, null,null)
    var showOnlyFullStars = false

    /**
     * rating dialog store
     */

    @StringRes
    var storeRatingTitleTextId = R.string.rating_dialog_store_title

    var storeRatingTitleText: String? = null

    @StringRes
    var storeRatingMessageTextId = R.string.rating_dialog_store_message
    var storeRatingMessageText: String? = null
    var rateNowButton: RateButton = RateButton(R.string.rating_dialog_store_button_rate_now, null,null)
    var additionalRateNowButtonClickListener: RateDialogClickListener? = null

    /**
     * rating dialog feedback
     */

    @StringRes
    var feedbackTitleTextId = R.string.rating_dialog_feedback_title
    var feedbackTitleText: String? = null
    var noFeedbackButton: RateButton =
        RateButton(R.string.rating_dialog_feedback_button_cancel, null, null)

    /**
     * rating dialog mail feedback
     */

    @StringRes
    var mailFeedbackMessageTextId = R.string.rating_dialog_feedback_mail_message
    var mailFeedbackMessageText: String? = null
    var mailFeedbackButton: RateButton =
        RateButton(R.string.rating_dialog_feedback_mail_button_send, null, null)
    var mailSettings: MailSettings? = null
    var additionalMailFeedbackButtonClickListener: RateDialogClickListener? = null

    /**
     * rating dialog custom feedback
     */

    var useCustomFeedback = false

    @StringRes
    var customFeedbackMessageTextId = R.string.rating_dialog_feedback_custom_message
    var customFeedbackMessageText: String? = null
    var customFeedbackButton: CustomFeedbackButton =
        CustomFeedbackButton(R.string.rating_dialog_feedback_custom_button_submit, null, null)

    /**
     * other settings
     */

    var cancelable = false
    var dialogCancelListener: (() -> Unit)? = null

    /**
     * Google in-app review
     */

    var useGoogleInAppReview = false
    var googleInAppReviewCompleteListener: ((Boolean) -> Unit)? = null
}
