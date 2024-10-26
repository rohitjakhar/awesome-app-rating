package com.rohitjakhar.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.rohitjakhar.core.R
import com.rohitjakhar.core.dialog.DialogOptions
import com.rohitjakhar.core.logging.RatingLogger
import com.rohitjakhar.core.preferences.PreferenceUtil

object FeedbackUtils {
    const val GOOGLE_PLAY_WEB_URL = "https://play.google.com/store/apps/details?id="
    const val GOOGLE_PLAY_IN_APP_URL = "market://details?id="
    const val URI_SCHEME_MAIL_TO = "mailto:"

    fun openPlayStoreListing(context: Context) {
        try {
            val uri = Uri.parse(GOOGLE_PLAY_IN_APP_URL + context.packageName)
            com.rohitjakhar.core.logging.RatingLogger.info(context.getString(R.string.rating_dialog_log_feedback_utils_open_rating_url, uri))
            val googlePlayIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(googlePlayIntent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            com.rohitjakhar.core.logging.RatingLogger.info(context.getString(R.string.rating_dialog_log_feedback_utils_play_store_not_found))
            val uri = Uri.parse(GOOGLE_PLAY_WEB_URL + context.packageName)
            com.rohitjakhar.core.logging.RatingLogger.info(context.getString(R.string.rating_dialog_log_feedback_utils_open_rating_url_web, uri))
            val googlePlayIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(googlePlayIntent)
        }
    }

    fun openMailFeedback(context: Context, settings: com.rohitjakhar.core.preferences.MailSettings) {
        val mailIntent: Intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(URI_SCHEME_MAIL_TO)
            putExtra(Intent.EXTRA_EMAIL, arrayOf(settings.mailAddress))
            putExtra(Intent.EXTRA_SUBJECT, settings.subject)
            putExtra(Intent.EXTRA_TEXT, settings.text)
        }

        if (mailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mailIntent)
            com.rohitjakhar.core.logging.RatingLogger.info(context.getString(R.string.rating_dialog_log_feedback_utils_open_mail_app))
        } else {
            val errorMessage = settings.errorToastMessage
                ?: context.getString(R.string.rating_dialog_feedback_mail_no_mail_error)
            com.rohitjakhar.core.logging.RatingLogger.error(context.getString(R.string.rating_dialog_log_feedback_utils_mail_app_not_found))
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun canShowNeverAskButton(dialogOptions: DialogOptions, context: Context): Boolean {
        val countOfLaterButtonClicksToShowNeverButton = dialogOptions.countOfLaterButtonClicksToShowNeverButton
        val numberOfLaterButtonClicks = PreferenceUtil.getNumberOfLaterButtonClicks(context)
        RatingLogger.debug(context.getString(R.string.rating_dialog_log_rate_later_button_was_clicked, numberOfLaterButtonClicks))
        if (countOfLaterButtonClicksToShowNeverButton > numberOfLaterButtonClicks) {
            RatingLogger.info(
                context.getString(R.string.rating_dialog_log_rate_later_button_dont_show_never, countOfLaterButtonClicksToShowNeverButton),
            )
            return false
        }
        return true
    }
}
