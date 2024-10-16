package com.rohitjakhar.ratingdialog.compose.dialog.compose_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rohitjakhar.ratingdialog.compose.R
import com.rohitjakhar.ratingdialog.compose.dialog.DialogOptions
import com.rohitjakhar.ratingdialog.compose.logging.RatingLogger
import com.rohitjakhar.ratingdialog.compose.preferences.PreferenceUtil

@Composable
internal fun RatingCustomFeedbackDialog(
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

    var text by remember { mutableStateOf("") }
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
                    // Title Text
                    Text(
                        text = stringResource(id = R.string.rating_dialog_feedback_title),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    OutlinedTextField(
                        value = text,

                        onValueChange = { text = it },
                        placeholder = { Text(text = stringResource(id = R.string.rating_dialog_feedback_custom_message))},
                        label = { Text(text = "Write your feedback")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp), // Set a fixed height to make it larger
                        maxLines = 4, // Allow up to 4 lines of text
                        shape = RoundedCornerShape(12.dp), // Rounded corners for the outline
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent, // Transparent background when not focused
                            focusedContainerColor = Color.Transparent, // Transparent background when focused
                            focusedBorderColor = Color.Blue, // Border color when focused
                            unfocusedBorderColor = Color.Gray, // Border color when not focused
                        ),
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        RatingLogger.info(context.getString(R.string.rating_dialog_log_custom_feedback_button_clicked))
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
                            RatingLogger.info(context.getString(R.string.rating_dialog_log_no_feedback_button_clicked))
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
                            RatingLogger.info(context.getString(R.string.rating_dialog_log_no_feedback_button_clicked))
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
