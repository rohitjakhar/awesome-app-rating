package com.rohitjakhar.compose.ratingdialog.dialog.compose_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.drawable.toBitmap
import com.rohitjakhar.compose.ratingdialog.R
import com.rohitjakhar.compose.ratingdialog.dialog.DialogOptions
import com.rohitjakhar.compose.ratingdialog.logging.RatingLogger
import com.rohitjakhar.compose.ratingdialog.preferences.PreferenceUtil
import com.rohitjakhar.compose.ratingdialog.utils.FeedbackUtils
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.RatingInterval

@Composable
internal fun RatingOverviewDialog(
    onDismissRequest: () -> Unit,
    onRatingSelected: (Float) -> Unit,
    dialogOptions: DialogOptions
) {
    val context = LocalContext.current
    val dialogProperty by remember {
        mutableStateOf(
            DialogProperties(
                dismissOnBackPress = dialogOptions.cancelable,
                dismissOnClickOutside = dialogOptions.cancelable,
            ),
        )
    }

    val countOfLaterButtonClicksToShowNeverButton = dialogOptions.countOfLaterButtonClicksToShowNeverButton
    var rating by remember { mutableStateOf(4f) }
    val numberOfLaterButtonClicks = PreferenceUtil.getNumberOfLaterButtonClicks(context)
    val imageBackground = ImageBitmap.imageResource(id = R.drawable.star_background)
    val imageForeground = ImageBitmap.imageResource(id = R.drawable.star_foreground)
    var showDialog by remember {
        mutableStateOf(true)
    }
    val iconBitmap = dialogOptions.iconDrawable?.let {
        RatingLogger.info(context.getString(R.string.rating_dialog_log_use_custom_rating_dialog_icon))
        it
    } ?: run {
        RatingLogger.info(context.getString(R.string.rating_dialog_log_use_app_icon))
        context.packageManager.getApplicationIcon(context.applicationInfo)
    }
    RatingLogger.debug(context.getString(R.string.rating_dialog_log_rate_later_button_was_clicked, numberOfLaterButtonClicks))
    if (countOfLaterButtonClicksToShowNeverButton > numberOfLaterButtonClicks) {
        RatingLogger.info(
            context.getString(R.string.rating_dialog_log_rate_later_button_dont_show_never, countOfLaterButtonClicksToShowNeverButton),
        )
        return
    }

    if (showDialog) {
        AlertDialog(
            properties = dialogProperty,
            onDismissRequest = {
                showDialog = false
                onDismissRequest.invoke()
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // image
                    Image(
                        bitmap = (iconBitmap).toBitmap()
                            .asImageBitmap(), // Replace with your image
                        contentDescription = stringResource(id = R.string.rating_dialog_overview_image_content_description),
                        modifier = Modifier
                            .size(180.dp)
                            .padding(vertical = dimensionResource(id = R.dimen.margin_big)),
                    )

                    // Title Text
                    Text(
                        text = stringResource(id = dialogOptions.titleTextId),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    RatingBar(
                        rating = rating,
                        space = 2.dp,
                        imageEmpty = imageBackground,
                        imageFilled = imageForeground,
                        itemSize = 60.dp,
                        ratingInterval = if (dialogOptions.showOnlyFullStars) RatingInterval.Full else RatingInterval.Unconstrained,
                        onRatingChangeFinished = {
                            rating = it
                        },
                        onRatingChange = {
                            rating = it
                        },
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onRatingSelected.invoke(rating)
                    },
                ) {
                    Text(text = stringResource(id = dialogOptions.confirmButton.textId))
                }
            },
            dismissButton = {
                when {
                    FeedbackUtils.canShowNeverAskButton(dialogOptions,context) && dialogOptions.rateNeverButton?.textId != null -> {
                        TextButton(
                            onClick = {
                                RatingLogger.debug(context.getString(R.string.rating_dialog_log_preference_dont_show_again))
                                PreferenceUtil.setDoNotShowAgain(context)
                                showDialog = false
                                onDismissRequest.invoke()
                            },
                        ) {
                            Text(text = stringResource(id = dialogOptions.rateNeverButton?.textId!!))
                        }
                    }
                    else -> {
                        TextButton(
                            onClick = {
                                RatingLogger.info(context.getString(R.string.rating_dialog_log_rate_later_button_clicked))
                                showDialog = false
                                onDismissRequest.invoke()
                            },
                        ) {
                            Text(text = stringResource(id = dialogOptions.rateLaterButton.textId))
                        }
                    }
                }
            },
        )
    }
}
