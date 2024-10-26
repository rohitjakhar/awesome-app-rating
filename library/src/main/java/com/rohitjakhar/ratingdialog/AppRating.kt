package com.rohitjakhar.ratingdialog

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.core.app.ComponentActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.rohitjakhar.core.buttons.ConfirmButtonClickListener
import com.rohitjakhar.core.buttons.CustomFeedbackButtonClickListener
import com.rohitjakhar.core.buttons.RateButton
import com.rohitjakhar.core.buttons.RateDialogClickListener
import com.rohitjakhar.core.dialog.DialogOptions
import com.rohitjakhar.core.dialog.DialogConfigModel
import com.rohitjakhar.ratingdialog.dialog.RateDialogFragment
import com.rohitjakhar.core.preferences.toFloat
import com.rohitjakhar.core.preferences.toRatingThreshold
import com.rohitjakhar.core.utils.FeedbackUtils

object AppRating {

    fun reset(context: Context) {
        com.rohitjakhar.core.preferences.PreferenceUtil.reset(context)
        com.rohitjakhar.core.logging.RatingLogger.warn(context.getString(R.string.rating_dialog_log_settings_reset))
    }

    fun isDialogAgreed(context: Context) = com.rohitjakhar.core.preferences.PreferenceUtil.isDialogAgreed(context)

    fun wasLaterButtonClicked(context: Context) = com.rohitjakhar.core.preferences.PreferenceUtil.wasLaterButtonClicked(context)

    fun wasNeverButtonClicked(context: Context) = com.rohitjakhar.core.preferences.PreferenceUtil.isDoNotShowAgain(context)

    fun getNumberOfLaterButtonClicks(context: Context) = com.rohitjakhar.core.preferences.PreferenceUtil.getNumberOfLaterButtonClicks(context)

    fun openMailFeedback(context: Context, mailSettings: com.rohitjakhar.core.preferences.MailSettings) = FeedbackUtils.openMailFeedback(context, mailSettings)

    fun openPlayStoreListing(context: Context) = FeedbackUtils.openPlayStoreListing(context)

    data class Builder(var componentActivity: ComponentActivity) {
        internal var isDebug = false
        internal var reviewManager: ReviewManager? = null
        private var dialogOptions = DialogOptions()
        private var dialogConfigModel: DialogConfigModel? = null

        internal constructor(componentActivity: ComponentActivity, dialogOptions: DialogOptions) : this(componentActivity) {
            this.dialogOptions = dialogOptions
        }

        fun setIconDrawable(iconDrawable: Drawable?) = apply {
            dialogOptions.iconDrawable = iconDrawable
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_use_custom_icon))
        }

        fun setCustomTheme(customTheme: Int) = apply {
            dialogOptions.customTheme = customTheme
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_use_custom_theme))
        }

        fun setRateLaterButtonTextId(@StringRes rateLaterButtonTextId: Int) = apply {
            dialogOptions.rateLaterButton.textId = rateLaterButtonTextId
        }

        fun setRateLaterButtonClickListener(rateLaterButtonClickListener: RateDialogClickListener) = apply {
            dialogOptions.rateLaterButton.rateDialogClickListener = rateLaterButtonClickListener
        }

        fun setConfigConditions(dialogConfigModel: DialogConfigModel) = apply{
            applyCondition(dialogConfigModel)
        }

        fun applyCondition(dialogConfigModel: DialogConfigModel) {
            dialogConfigModel.ratingThreshold?.toFloat()?.let {
                setRatingThreshold(it.toFloat())
            }
            dialogConfigModel.countAppLaunch?.let {
                dialogOptions.countAppLaunch = it
                com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_dont_count_app_launch))
            }
            dialogConfigModel.cancelable?.let {
                dialogOptions.cancelable = it
                setCancelable(it)
            }
            dialogConfigModel.useCustomFeedback?.let {
                dialogOptions.useCustomFeedback = it
                setUseCustomFeedback(it)
            }
            dialogConfigModel.minimumDays?.let {
                setMinimumDays(it)
            }

            dialogConfigModel.countOfLaterButtonClicksToShowNeverButton?.let {
                dialogOptions.countOfLaterButtonClicksToShowNeverButton = it
            }
            dialogOptions.confirmButton.text = dialogConfigModel.confirmButtonText
            dialogOptions.customFeedbackMessageText = dialogConfigModel.customFeedbackButtonText
            dialogOptions.feedbackTitleText = dialogConfigModel.feedbackTitleText
            dialogOptions.messageText = dialogConfigModel.messageText
            dialogOptions.rateLaterButton.text = dialogConfigModel.rateLaterButtonText
            dialogOptions.storeRatingMessageText = dialogConfigModel.storeRatingMessageText
            dialogOptions.storeRatingTitleText = dialogConfigModel.storeRatingTitleText
            dialogOptions.titleText = dialogConfigModel.titleText
            dialogOptions.iconUri = dialogConfigModel.iconUri
            dialogConfigModel.useCustomFeedback?.let {
                setUseCustomFeedback(it)
            }
            dialogConfigModel.mailSetting?.let {
                dialogOptions.mailSettings = it
                setMailSettingsForFeedbackDialog(it)
            }

            dialogConfigModel.minimumLaunchTimes?.let {
                setMinimumLaunchTimes(it)
            }
            dialogConfigModel.showFullStarOnly?.let {
                dialogOptions.showOnlyFullStars = true
                setShowOnlyFullStars(it)
            }
            dialogConfigModel.useGoogleInAppReview?.let {
                dialogOptions.useGoogleInAppReview = it
            }
        }

        fun showRateNeverButton(
            @StringRes rateNeverButtonTextId: Int = R.string.rating_dialog_button_rate_never,
            rateNeverButtonClickListener: RateDialogClickListener? = null,
        ) = apply {
            dialogOptions.rateNeverButton = RateButton(rateNeverButtonTextId, null, rateNeverButtonClickListener)
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_show_rate_never_button))
        }

        fun showRateNeverButtonAfterNTimes(
            @StringRes rateNeverButtonTextId: Int = R.string.rating_dialog_button_rate_never,
            rateNeverButtonClickListener: RateDialogClickListener? = null,
            countOfLaterButtonClicks: Int,
        ) = apply {
            dialogOptions.rateNeverButton = RateButton(rateNeverButtonTextId, null, rateNeverButtonClickListener)
            dialogOptions.countOfLaterButtonClicksToShowNeverButton = countOfLaterButtonClicks
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_show_rate_never_button_later, countOfLaterButtonClicks))
        }

        /**
         * rating dialog overview
         */

        fun setTitleTextId(@StringRes titleTextId: Int) = apply {
            dialogOptions.titleTextId = titleTextId
        }

        fun setMessageTextId(@StringRes messageTextId: Int) = apply {
            dialogOptions.messageTextId = messageTextId
        }

        fun setConfirmButtonTextId(@StringRes confirmButtonTextId: Int) = apply {
            dialogOptions.confirmButton.textId = confirmButtonTextId
        }

        fun setConfirmButtonClickListener(confirmButtonClickListener: ConfirmButtonClickListener) = apply {
            dialogOptions.confirmButton.confirmButtonClickListener = confirmButtonClickListener
        }

        fun setShowOnlyFullStars(showOnlyFullStars: Boolean) = apply {
            dialogOptions.showOnlyFullStars = showOnlyFullStars
        }

        /**
         * rating dialog store
         */

        fun setStoreRatingTitleTextId(@StringRes storeRatingTitleTextId: Int) = apply {
            dialogOptions.storeRatingTitleTextId = storeRatingTitleTextId
        }

        fun setStoreRatingMessageTextId(@StringRes storeRatingMessageTextId: Int) = apply {
            dialogOptions.storeRatingMessageTextId = storeRatingMessageTextId
        }

        fun setRateNowButtonTextId(@StringRes rateNowButtonTextId: Int) = apply {
            dialogOptions.rateNowButton.textId = rateNowButtonTextId
        }

        fun overwriteRateNowButtonClickListener(rateNowButtonClickListener: RateDialogClickListener) = apply {
            dialogOptions.rateNowButton.rateDialogClickListener = rateNowButtonClickListener
        }

        fun setAdditionalRateNowButtonClickListener(additionalRateNowButtonClickListener: RateDialogClickListener) =
            apply { dialogOptions.additionalRateNowButtonClickListener = additionalRateNowButtonClickListener }

        /**
         * rating dialog feedback
         */

        fun setFeedbackTitleTextId(@StringRes feedbackTitleTextId: Int) = apply {
            dialogOptions.feedbackTitleTextId = feedbackTitleTextId
        }

        fun setNoFeedbackButtonTextId(@StringRes noFeedbackButtonTextId: Int) = apply {
            dialogOptions.noFeedbackButton.textId = noFeedbackButtonTextId
        }

        fun setNoFeedbackButtonClickListener(noFeedbackButtonClickListener: RateDialogClickListener) = apply {
            dialogOptions.noFeedbackButton.rateDialogClickListener = noFeedbackButtonClickListener
        }

        /**
         * rating dialog mail feedback
         */

        fun setMailFeedbackMessageTextId(@StringRes feedbackMailMessageTextId: Int) = apply {
            dialogOptions.mailFeedbackMessageTextId = feedbackMailMessageTextId
        }

        fun setMailSettingsForFeedbackDialog(mailSettings: com.rohitjakhar.core.preferences.MailSettings) = apply {
            dialogOptions.mailSettings = mailSettings
        }

        fun setMailFeedbackButtonTextId(@StringRes mailFeedbackButtonTextId: Int) = apply {
            dialogOptions.mailFeedbackButton.textId = mailFeedbackButtonTextId
        }

        fun overwriteMailFeedbackButtonClickListener(mailFeedbackButtonClickListener: RateDialogClickListener) = apply {
            dialogOptions.mailFeedbackButton.rateDialogClickListener = mailFeedbackButtonClickListener
        }

        fun setAdditionalMailFeedbackButtonClickListener(additionalMailFeedbackButtonClickListener: RateDialogClickListener) = apply {
            dialogOptions.additionalMailFeedbackButtonClickListener = additionalMailFeedbackButtonClickListener
        }

        /**
         * rating dialog custom feedback
         */

        fun setUseCustomFeedback(useCustomFeedback: Boolean) = apply {
            dialogOptions.useCustomFeedback = useCustomFeedback
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_use_custom_feedback, useCustomFeedback))
        }

        fun setCustomFeedbackMessageTextId(@StringRes feedbackCustomMessageTextId: Int) = apply {
            dialogOptions.customFeedbackMessageTextId = feedbackCustomMessageTextId
        }

        fun setCustomFeedbackButtonTextId(@StringRes customFeedbackButtonTextId: Int) = apply {
            dialogOptions.customFeedbackButton.textId = customFeedbackButtonTextId
        }

        fun setCustomFeedbackButtonClickListener(customFeedbackButtonClickListener: CustomFeedbackButtonClickListener) = apply {
            dialogOptions.customFeedbackButton.customFeedbackButtonClickListener = customFeedbackButtonClickListener
        }

        /**
         * other settings
         */

        fun setRatingThreshold(ratingThreshold: com.rohitjakhar.core.preferences.RatingThreshold) = apply {
            dialogOptions.ratingThreshold = ratingThreshold
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_set_rating_threshold, ratingThreshold.toFloat()))
        }

        fun setRatingThreshold(ratingThreshold: Float) = apply {
            dialogOptions.ratingThreshold = ratingThreshold.toRatingThreshold()
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_set_rating_threshold, ratingThreshold.toInt()))
        }

        fun setCancelable(cancelable: Boolean) = apply {
            dialogOptions.cancelable = cancelable
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_set_cancelable, cancelable))
        }

        fun setDialogCancelListener(dialogCancelListener: () -> Unit) = apply {
            dialogOptions.dialogCancelListener = dialogCancelListener
        }

        fun setMinimumLaunchTimes(launchTimes: Int) = apply {
            com.rohitjakhar.core.preferences.PreferenceUtil.setMinimumLaunchTimes(componentActivity, launchTimes)
        }

        fun setMinimumLaunchTimesToShowAgain(launchTimesToShowAgain: Int) = apply {
            com.rohitjakhar.core.preferences.PreferenceUtil.setMinimumLaunchTimesToShowAgain(componentActivity, launchTimesToShowAgain)
        }

        fun setMinimumDays(minimumDays: Int) = apply {
            com.rohitjakhar.core.preferences.PreferenceUtil.setMinimumDays(componentActivity, minimumDays)
        }

        fun setMinimumDaysToShowAgain(minimumDaysToShowAgain: Int) = apply {
            com.rohitjakhar.core.preferences.PreferenceUtil.setMinimumDaysToShowAgain(componentActivity, minimumDaysToShowAgain)
        }

        fun setCustomCondition(customCondition: () -> Boolean) = apply {
            dialogOptions.customCondition = customCondition
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_set_custom_condition))
        }

        fun setCustomConditionToShowAgain(customConditionToShowAgain: () -> Boolean) = apply {
            dialogOptions.customConditionToShowAgain = customConditionToShowAgain
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_set_custom_condition_to_show_again))
        }

        fun dontCountThisAsAppLaunch() = apply {
            dialogOptions.countAppLaunch = false
            com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_dont_count_app_launch))
        }

        fun setLoggingEnabled(isLoggingEnabled: Boolean) = apply {
            com.rohitjakhar.core.logging.RatingLogger.isLoggingEnabled = isLoggingEnabled
        }

        fun setDebug(isDebug: Boolean) = apply {
            this.isDebug = isDebug
            com.rohitjakhar.core.logging.RatingLogger.warn(componentActivity.getString(R.string.rating_dialog_log_set_debug, isDebug))
        }

        /**
         * Google in-app review
         */

        /**
         * If this method is called, the in-app review from Google will be used instead of
         * the library dialog.
         */
        fun useGoogleInAppReview() = apply {
            reviewManager = ReviewManagerFactory.create(componentActivity)
            dialogOptions.useGoogleInAppReview = true
            com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_use_in_app_review))
        }

        /**
         * The completion listener will be invoked with true if the in-app review flow started
         * correctly (otherwise false).
         * Note: true doesn't mean that the in-app review from Google was displayed.
         */
        fun setGoogleInAppReviewCompleteListener(googleInAppReviewCompleteListener: (Boolean) -> Unit) = apply {
            dialogOptions.googleInAppReviewCompleteListener = googleInAppReviewCompleteListener
        }

        /**
         * This method will return null if the in-app review from Google is used.
         */
        fun create(): DialogFragment? = when {
            dialogOptions.useGoogleInAppReview -> {
                com.rohitjakhar.core.logging.RatingLogger.warn(componentActivity.getString(R.string.rating_dialog_log_create_not_possible_with_in_app_review))
                null
            }

            else -> RateDialogFragment.newInstance(dialogOptions)
        }

        fun showNow() = when {
            dialogOptions.useGoogleInAppReview -> {
                com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_show_in_app_review))
                showGoogleInAppReview()
            }

            else -> {
                com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_show_library_dialog))
                (componentActivity as? FragmentActivity)?.let { RateDialogFragment.newInstance(dialogOptions).show(it.supportFragmentManager, TAG) }
                    ?: com.rohitjakhar.core.logging.RatingLogger.error(componentActivity.getString(R.string.rating_dialog_log_error_extend_from_fragment_activity))
            }
        }

        fun showIfMeetsConditions(): Boolean {
            (componentActivity as? FragmentActivity)?.let {
                if (it.supportFragmentManager.findFragmentByTag(TAG) != null) {
                    com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_stop_checking_conditions))
                    return false
                }
            }

            if (dialogOptions.countAppLaunch) {
                com.rohitjakhar.core.logging.RatingLogger.debug(componentActivity.getString(R.string.rating_dialog_log_app_launch_counted))
                com.rohitjakhar.core.preferences.PreferenceUtil.increaseLaunchTimes(componentActivity)
            } else {
                com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_app_launch_not_counted))
            }

            return if (isDebug || com.rohitjakhar.core.preferences.ConditionsChecker.shouldShowDialog(componentActivity, dialogOptions)) {
                com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_show_rating_dialog_now))
                showNow()
                true
            } else {
                com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_dont_show_rating_dialog_now))
                false
            }
        }

        internal fun showGoogleInAppReview() {
            val requestTask = reviewManager?.requestReviewFlow() ?: run {
                onGoogleInAppReviewFailure(componentActivity.getString(R.string.rating_dialog_log_in_app_review_review_manager_is_null))
                return
            }
            requestTask.addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    val reviewInfo = request.result ?: run {
                        onGoogleInAppReviewFailure(componentActivity.getString(R.string.rating_dialog_log_in_app_review_initial_request_is_null))
                        return@addOnCompleteListener
                    }
                    val flow = reviewManager?.launchReviewFlow(componentActivity, reviewInfo) ?: run {
                        onGoogleInAppReviewFailure(componentActivity.getString(R.string.rating_dialog_log_in_app_review_initial_request_is_null))
                        return@addOnCompleteListener
                    }
                    flow.addOnCompleteListener { task ->
                        com.rohitjakhar.core.logging.RatingLogger.info(componentActivity.getString(R.string.rating_dialog_log_in_app_review_completed))
                        com.rohitjakhar.core.preferences.PreferenceUtil.onGoogleInAppReviewFlowCompleted(componentActivity)
                        dialogOptions.googleInAppReviewCompleteListener?.invoke(task.isSuccessful)
                            ?: com.rohitjakhar.core.logging.RatingLogger.warn(componentActivity.getString(R.string.rating_dialog_log_in_app_review_no_complete_listener))
                    }
                } else {
                    onGoogleInAppReviewFailure(componentActivity.getString(R.string.rating_dialog_log_in_app_review_initial_request_not_successful))
                }
            }
        }

        private fun onGoogleInAppReviewFailure(additionalInfo: String) {
            com.rohitjakhar.core.logging.RatingLogger.warn(componentActivity.getString(R.string.rating_dialog_log_in_app_review_not_successful, additionalInfo))
            dialogOptions.googleInAppReviewCompleteListener?.invoke(false)
                ?: com.rohitjakhar.core.logging.RatingLogger.warn(componentActivity.getString(R.string.rating_dialog_log_in_app_review_no_complete_listener))
        }

        companion object {
            private const val TAG = "AwesomeAppRatingDialog"
        }
    }
}
