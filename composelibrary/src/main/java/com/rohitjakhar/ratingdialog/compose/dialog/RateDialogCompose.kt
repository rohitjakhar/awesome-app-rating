package com.rohitjakhar.ratingdialog.compose.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import com.rohitjakhar.ratingdialog.compose.R
import com.rohitjakhar.ratingdialog.compose.dialog.compose_dialog.MailFeedbackDialog
import com.rohitjakhar.ratingdialog.compose.dialog.compose_dialog.RatingCustomFeedbackDialog
import com.rohitjakhar.ratingdialog.compose.dialog.compose_dialog.RatingOverviewDialog
import com.rohitjakhar.ratingdialog.compose.dialog.compose_dialog.RatingStoreDialog
import com.rohitjakhar.ratingdialog.compose.logging.RatingLogger
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil.PREF_KEY_DIALOG_SHOW_LATER
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil.PREF_KEY_LAUNCH_TIMES
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil.PREF_KEY_REMIND_TIMESTAMP
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil.getPreferences
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil.increaseNumberOfLaterButtonClicks
import com.rohitjakhar.ratingdialog.compose.preferences.toFloat

@Composable
internal fun RateDialogCompose(
    dialogOptions: DialogOptions,
    dialogType: DialogType = DialogType.RATING_OVERVIEW,
    onDismissRequest: () -> Unit,
    onRatingSelected: (Float) -> Unit
) {
    val context = LocalContext.current
    var dialogType1 by remember {
        mutableStateOf(dialogType)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (dialogType1) {
            DialogType.RATING_OVERVIEW -> {
                RatingOverviewDialog(
                    onDismissRequest = {
                        RatingLogger.verbose(context.getString(R.string.rating_dialog_log_preference_later_button_clicked))
                        getPreferences(context).edit {
                            putLong(PREF_KEY_REMIND_TIMESTAMP, System.currentTimeMillis())
                            putInt(PREF_KEY_LAUNCH_TIMES, 0)
                            putBoolean(PREF_KEY_DIALOG_SHOW_LATER, true)
                        }
                        increaseNumberOfLaterButtonClicks(context)
                        onDismissRequest.invoke()
                    },
                    onRatingSelected = {
                        dialogType1 = when {
                            it >= dialogOptions.ratingThreshold.toFloat() -> {
                                RatingLogger.info(context.getString(R.string.rating_dialog_log_rating_overview_above_threshold))
                                DialogType.RATING_STORE
                            }

                            dialogOptions.useCustomFeedback -> {
                                RatingLogger.info(context.getString(R.string.rating_dialog_log_rating_overview_below_threshold_with_custom_feedback))
                                PreferenceUtil.setDialogAgreed(context)
                                DialogType.FEEDBACK_CUSTOM
                            }

                            else -> {
                                RatingLogger.info(context.getString(R.string.rating_dialog_log_rating_overview_below_threshold_without_custom_feedback))
                                PreferenceUtil.setDialogAgreed(context)
                                DialogType.FEEDBACK_MAIL
                            }
                        }
                    },
                    dialogOptions = dialogOptions,
                )
            }

            DialogType.RATING_STORE -> {
                RatingLogger.debug(context.getString(R.string.rating_dialog_log_rating_store_creating_dialog))
                RatingStoreDialog(
                    onDismissRequest = onDismissRequest,
                    onRatingSelected = {},
                    dialogOptions = dialogOptions,
                )
            }

            DialogType.FEEDBACK_MAIL -> {
                RatingLogger.debug(context.getString(R.string.rating_dialog_log_mail_feedback_creating_dialog))
                MailFeedbackDialog(
                    onDismissRequest = onDismissRequest,
                    onRatingSelected = {},
                    dialogOptions = dialogOptions,
                )
            }

            DialogType.FEEDBACK_CUSTOM -> {
                RatingLogger.debug(context.getString(R.string.rating_dialog_log_custom_feedback_creating_dialog))
                RatingCustomFeedbackDialog(
                    onDismissRequest = onDismissRequest,
                    onRatingSelected = {},
                    dialogOptions = dialogOptions,
                )
            }
        }

    }
}
