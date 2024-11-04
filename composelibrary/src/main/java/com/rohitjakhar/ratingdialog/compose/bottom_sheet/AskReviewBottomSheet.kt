package com.rohitjakhar.ratingdialog.compose.bottom_sheet

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rohitjakhar.core.dialog.DialogOptions
import com.rohitjakhar.core.dialog.DialogType
import com.rohitjakhar.core.logging.RatingLogger
import com.rohitjakhar.core.preferences.PreferenceUtil
import com.rohitjakhar.core.utils.FeedbackUtils
import com.rohitjakhar.ratingdialog.compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AskReviewBottomSheet(
    modifier: Modifier = Modifier,
    dialogOptions: DialogOptions,
    dialogType: DialogType = DialogType.RATING_OVERVIEW,
    onDismissRequest: () -> Unit,
    onRatingSelected: (Float) -> Unit
) {
    val context = LocalContext.current
    var dialogType1 by remember {
        mutableStateOf(dialogType)
    }
    when (dialogType) {
        DialogType.RATING_OVERVIEW -> {
            ModalBottomSheet(onDismissRequest = onDismissRequest) {
                Text("Are you enjoying the app?")
                Row {
                    TextButton(
                        onClick = {
                            RatingLogger.info(context.getString(R.string.rating_dialog_log_rating_overview_above_threshold))
                            dialogType1 = DialogType.RATING_STORE
                        },
                    ) {
                        Text("Yes")
                    }
                    TextButton(
                        onClick = {
                            dialogType1 = when {
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
                    ) {
                        Text("No")
                    }
                }
            }
        }

        DialogType.RATING_STORE -> {
            ModalBottomSheet(onDismissRequest = onDismissRequest) {
                Text("Please Give Rating on Store")
                Row {
                    TextButton(
                        onClick = {
                            PreferenceUtil.setDialogAgreed(context)
                            FeedbackUtils.openPlayStoreListing(context)
                        },
                    ) {
                        Text("Yes")
                    }
                    TextButton(onClick = {}) {
                        Text("No")
                    }
                }
            }
        }

        DialogType.FEEDBACK_MAIL -> {
            ModalBottomSheet(onDismissRequest = onDismissRequest) {

            }
        }

        DialogType.FEEDBACK_CUSTOM -> {
            ModalBottomSheet(onDismissRequest = onDismissRequest) {

            }
        }
    }
}
