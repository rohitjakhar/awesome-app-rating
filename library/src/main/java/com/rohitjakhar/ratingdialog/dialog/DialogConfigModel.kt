package com.rohitjakhar.ratingdialog.dialog

import com.rohitjakhar.ratingdialog.preferences.MailSettings

data class DialogConfigModel(
    var cancelable: Boolean? = null,
    var useCustomFeedback: Boolean? = null,
    var mailSetting: MailSettings? = null,
    var customFeedbackMessageText: String? = null,
    var customFeedbackButtonText: String? = null,
    var useGoogleInAppReview: Boolean? = null,
    var feedbackTitleText: String? = null,
    var rateLaterButtonText: String? = null,
    var iconUri: String? = null,
    var ratingThreshold: Float? = null,
    var countAppLaunch: Boolean? = null,
    var countOfLaterButtonClicksToShowNeverButton: Int? = null,
    var titleText: String? = null,
    var messageText: String? = null,
    var confirmButtonText: String? = null,
    var showFullStarOnly: Boolean? = null,
    var storeRatingTitleText: String? = null,
    var storeRatingMessageText: String? = null,
    var minimumDays: Int? = null,
    var minimumLaunchTimes: Int? = null,
)
