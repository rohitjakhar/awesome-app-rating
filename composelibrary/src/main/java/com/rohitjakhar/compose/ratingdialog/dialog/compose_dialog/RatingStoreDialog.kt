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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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

@Composable
internal fun RatingStoreDialog(
    onDismissRequest: () -> Unit,
    onRatingSelected: () -> Unit,
    dialogOptions: DialogOptions,
) {
    val context = LocalContext.current
    val dialogProperty by remember {
        mutableStateOf(DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true))
    }

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
                        text = stringResource(id = R.string.rating_dialog_store_title),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = stringResource(id = R.string.rating_dialog_store_message),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        PreferenceUtil.setDialogAgreed(context)
                        FeedbackUtils.openPlayStoreListing(context)
                        showDialog = false
                        onRatingSelected.invoke()
                    },
                ) {
                    Text(text = stringResource(id = dialogOptions.rateNowButton.textId))
                }
            },
            dismissButton = {
                dialogOptions.rateNeverButton?.textId?.let {
                    TextButton(
                        onClick = {
                            PreferenceUtil.setDialogAgreed(context)
                            showDialog = false
                            onDismissRequest.invoke()
                        },
                    ) {
                        Text(text = stringResource(id = it))
                    }
                } ?: run {
                    TextButton(
                        onClick = {
                            showDialog = false
                            onDismissRequest.invoke()
                        },
                    ) {
                        Text(text = stringResource(id = dialogOptions.rateLaterButton.textId))
                    }
                }

            },
        )
    }
}
