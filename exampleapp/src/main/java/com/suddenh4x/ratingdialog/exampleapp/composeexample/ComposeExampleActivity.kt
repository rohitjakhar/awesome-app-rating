package com.suddenh4x.ratingdialog.exampleapp.composeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.rohitjakhar.compose.ratingdialog.AppRatingCompose
import com.rohitjakhar.compose.ratingdialog.preferences.MailSettings
import com.suddenh4x.ratingdialog.exampleapp.R
import com.rohitjakhar.compose.ratingdialog.preferences.RatingThreshold

class ComposeExampleActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State variables to manage which composable to show
            var showInAppReview by remember { mutableStateOf(false) }
            var showDefault by remember { mutableStateOf(false) }
            var showCustomIcon by remember { mutableStateOf(false) }
            var showMailFeedback by remember { mutableStateOf(false) }
            var showCustomFeedback by remember { mutableStateOf(false) }
            var showShowNever by remember { mutableStateOf(false) }
            var showNeverAfterThreeTimes by remember { mutableStateOf(false) }
            var showOnThirdClick by remember { mutableStateOf(false) }
            var showRatingThreshold by remember { mutableStateOf(false) }
            var showFullStarRating by remember { mutableStateOf(false) }
            var showCustomTexts by remember { mutableStateOf(false) }
            var showCancelable by remember { mutableStateOf(false) }
            var showCustomTheme by remember { mutableStateOf(false) }

            when {
                showInAppReview -> {
                    OnGoogleInAppReviewExampleButtonClicked()
                }

                showDefault -> {
                    OnDefaultExampleButtonClicked()
                }

                showCustomIcon -> {
                    OnCustomIconButtonClicked()
                }

                showCustomTheme -> {
                    OnCustomThemeButtonClicked()
                }

                showMailFeedback -> {
                    OnMailFeedbackButtonClicked()
                }

                showCustomFeedback -> {
                    OnCustomFeedbackButtonClicked()
                }

                showShowNever -> {
                    OnShowNeverButtonClicked()
                }

                showNeverAfterThreeTimes -> {
                    OnShowNeverButtonAfterThreeTimesClicked()
                }

                showOnThirdClick -> {
                    OnShowOnThirdClickButtonClicked()
                }

                showRatingThreshold -> {
                    OnRatingThresholdButtonClicked()
                }

                showFullStarRating -> {
                    OnFullStarRatingButtonClicked()
                }

                showCustomTexts -> {
                    OnCustomTextsButtonClicked()
                }

                showCancelable -> {
                    OnCancelableButtonClicked()
                }
            }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Example UI") },
                    )
                },
            ) { contentPadding ->
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.text_example_note_app_reset),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Button(onClick = { onResetButtonClicked() }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_reset))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_google_in_app_review),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showInAppReview = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_google_in_app_review))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_default),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showDefault = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_default))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_custom_icon),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showCustomIcon = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_custom_icon))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_mail_feedback),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showMailFeedback = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_mail_feedback))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_custom_feedback),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showCustomFeedback = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_custom_feedback))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_show_never),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showShowNever = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_show_never))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_show_never_after_three_times),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showNeverAfterThreeTimes = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_show_never_after_three_times))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_show_on_third_click),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showOnThirdClick = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_show_on_third_click))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_rating_threshold_4_and_a_half),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showRatingThreshold = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_rating_threshold_4_and_a_half))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_full_star_rating),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showFullStarRating = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_full_star_rating))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_custom_texts),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showCustomTexts = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_custom_texts))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_cancelable),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showCancelable = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_cancelable))
                    }

                    Text(
                        text = stringResource(id = R.string.text_example_custom_theme),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showCustomTheme = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_custom_theme))
                    }
                }
            }
        }
    }

    private fun onResetButtonClicked() {
        AppRatingCompose.reset(this)
        Toast.makeText(this, R.string.toast_reset, Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun OnGoogleInAppReviewExampleButtonClicked() {
        AppRatingCompose.Builder(this)
            .useGoogleInAppReview()
            .setGoogleInAppReviewCompleteListener { successful ->
                toastLiveData.postValue("Google in-app review completed (successful: $successful)")
            }
            .setDebug(true)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnDefaultExampleButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomIconButtonClicked() {
        val iconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_star_black, null)

        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setIconDrawable(iconDrawable)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnMailFeedbackButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setMailSettingsForFeedbackDialog(
                MailSettings(
                    "info@your-app.de",
                    "Feedback Mail",
                    "This is an example text.\n\nYou could set some device infos here.",
                ),
            )
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomFeedbackButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setUseCustomFeedback(true)
            .setCustomFeedbackButtonClickListener { userFeedbackText ->
                toastLiveData.postValue("Feedback: $userFeedbackText")
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowNeverButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .showRateNeverButton()
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowNeverButtonAfterThreeTimesClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .showRateNeverButtonAfterNTimes(countOfLaterButtonClicks = 3)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowOnThirdClickButtonClicked() {
        AppRatingCompose.Builder(this)
            .showRateNeverButton()
            .setMinimumLaunchTimes(3)
            .setMinimumDays(0)
            .setMinimumLaunchTimesToShowAgain(5)
            .setMinimumDaysToShowAgain(0)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnRatingThresholdButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setRatingThreshold(RatingThreshold.FOUR_AND_A_HALF)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnFullStarRatingButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setShowOnlyFullStars(true)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomTextsButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setRateNowButtonTextId(R.string.button_rate_now)
            .setRateLaterButtonTextId(R.string.button_rate_later)
            .showRateNeverButton(R.string.button_rate_never)
            .setTitleTextId(R.string.title_overview)
            .setMessageTextId(R.string.message_overview)
            .setConfirmButtonTextId(R.string.button_confirm)
            .setStoreRatingTitleTextId(R.string.title_store)
            .setStoreRatingMessageTextId(R.string.message_store)
            .setFeedbackTitleTextId(R.string.title_feedback)
            .setMailFeedbackMessageTextId(R.string.message_feedback)
            .setMailFeedbackButtonTextId(R.string.button_mail_feedback)
            .setNoFeedbackButtonTextId(R.string.button_no_feedback)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCancelableButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setCancelable(true)
            .setDialogCancelListener { toastLiveData.postValue("Dialog was canceled.") }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomThemeButtonClicked() {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setCustomTheme(R.style.AppTheme_CustomAlertDialog)
            .showIfMeetsConditions()
    }

    companion object {
        // The livedata is used so that no context is given into the click listeners. (NotSerializableException)
        private val toastLiveData: MutableLiveData<String> = MutableLiveData()
    }
}


