package com.rohitjakhar.ratingdialog.dialog

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import com.rohitjakhar.ratingdialog.R
import com.rohitjakhar.ratingdialog.buttons.ConfirmButton
import com.rohitjakhar.ratingdialog.buttons.CustomFeedbackButton
import com.rohitjakhar.ratingdialog.buttons.RateButton
import com.rohitjakhar.ratingdialog.buttons.RateDialogClickListener
import com.rohitjakhar.ratingdialog.preferences.MailSettings
import com.rohitjakhar.ratingdialog.preferences.RatingThreshold
import java.io.Serializable

internal class DialogOptions : Serializable {

    @Transient
    var iconDrawable: Drawable? = null
    var customTheme: Int = 0
    var rateLaterButton: RateButton = RateButton(R.string.rating_dialog_button_rate_later, null)
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

    @StringRes
    var messageTextId: Int? = null
    var confirmButton = ConfirmButton(R.string.rating_dialog_overview_button_confirm, null)
    var showOnlyFullStars = false

    /**
     * rating dialog store
     */

    @StringRes
    var storeRatingTitleTextId = R.string.rating_dialog_store_title

    @StringRes
    var storeRatingMessageTextId = R.string.rating_dialog_store_message
    var rateNowButton: RateButton = RateButton(R.string.rating_dialog_store_button_rate_now, null)
    var additionalRateNowButtonClickListener: RateDialogClickListener? = null

    /**
     * rating dialog feedback
     */

    @StringRes
    var feedbackTitleTextId = R.string.rating_dialog_feedback_title
    var noFeedbackButton: RateButton =
        RateButton(R.string.rating_dialog_feedback_button_cancel, null)

    /**
     * rating dialog mail feedback
     */

    @StringRes
    var mailFeedbackMessageTextId = R.string.rating_dialog_feedback_mail_message
    var mailFeedbackButton: RateButton =
        RateButton(R.string.rating_dialog_feedback_mail_button_send, null)
    var mailSettings: MailSettings? = null
    var additionalMailFeedbackButtonClickListener: RateDialogClickListener? = null

    /**
     * rating dialog custom feedback
     */

    var useCustomFeedback = false

    @StringRes
    var customFeedbackMessageTextId = R.string.rating_dialog_feedback_custom_message
    var customFeedbackButton: CustomFeedbackButton =
        CustomFeedbackButton(R.string.rating_dialog_feedback_custom_button_submit, null)

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
