package com.rohitjakhar.ratingdialog.exampleapp.composeexample

import android.os.Bundle
import android.util.Log
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
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.rohitjakhar.ratingdialog.compose.AppRatingCompose
import com.rohitjakhar.ratingdialog.compose.preferences.MailSettings
import com.rohitjakhar.ratingdialog.compose.preferences.RatingThreshold
import com.rohitjakhar.ratingdialog.dialog.DialogConfigModel
import com.suddenh4x.ratingdialog.exampleapp.R

class ComposeExampleActivity : ComponentActivity() {

    private val queue by lazy { Volley.newRequestQueue(this) }
    // paste your gist code here or alternate source.
    private val url = "https://gist.githubusercontent.com/rohitjakhar/78615c0b82b5ca97aa738e1bda00f8a3/raw/awsomappconfig.json"
    private val dataConfigModelFetchRequest by lazy {
        JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                // Parse response to DataModel
                val dataModel = Gson().fromJson(response.toString(), DialogConfigModel::class.java)
                //showConfigDialog(dataModel)
            },
            { error ->
                // Handle error
                error.printStackTrace()
            },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State variables to manage which composable to show
            var showInAppReview by remember { mutableStateOf(false) }
            var showCustomConfig by remember { mutableStateOf(false) }
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
                    OnGoogleInAppReviewExampleButtonClicked {
                        showInAppReview = false
                    }
                }

                showDefault -> {
                    OnDefaultExampleButtonClicked {
                        showDefault = false
                    }
                }

                showCustomIcon -> {
                    OnCustomIconButtonClicked {
                        showCustomIcon = false
                    }
                }

                showCustomTheme -> {
                    OnCustomThemeButtonClicked {
                        showCustomTheme = false
                    }
                }

                showMailFeedback -> {
                    OnMailFeedbackButtonClicked {
                        showMailFeedback = false
                    }
                }

                showCustomFeedback -> {
                    OnCustomFeedbackButtonClicked {
                        showCustomFeedback = false
                    }
                }

                showShowNever -> {
                    OnShowNeverButtonClicked {
                        showShowNever = false
                    }
                }

                showNeverAfterThreeTimes -> {
                    OnShowNeverButtonAfterThreeTimesClicked {
                        showNeverAfterThreeTimes = false
                    }
                }

                showOnThirdClick -> {
                    OnShowOnThirdClickButtonClicked {
                        showOnThirdClick = false
                    }
                }

                showRatingThreshold -> {
                    OnRatingThresholdButtonClicked {
                        showRatingThreshold = false
                    }
                }

                showFullStarRating -> {
                    OnFullStarRatingButtonClicked {
                        showFullStarRating = false
                    }
                }

                showCustomTexts -> {
                    OnCustomTextsButtonClicked {
                        showCustomTexts = false
                    }
                }

                showCancelable -> {
                    OnCancelableButtonClicked {
                        showCancelable = false
                    }
                }
                showCustomConfig -> {

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
                        text = stringResource(id = R.string.text_example_config),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { showInAppReview = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.button_example_custom_config))
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
    private fun OnGoogleInAppReviewExampleButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .useGoogleInAppReview()
            .setGoogleInAppReviewCompleteListener { successful ->
                toastLiveData.postValue("Google in-app review completed (successful: $successful)")
            }
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .setDebug(true)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnDefaultExampleButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomIconButtonClicked(onClose: () -> Unit) {
        val iconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_star_black, null)
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setIconDrawable(iconDrawable)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnMailFeedbackButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
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
    private fun OnCustomFeedbackButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setUseCustomFeedback(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .setCustomFeedbackButtonClickListener { userFeedbackText ->
                toastLiveData.postValue("Feedback: $userFeedbackText")
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowNeverButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showRateNeverButton()
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowNeverButtonAfterThreeTimesClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showRateNeverButtonAfterNTimes(countOfLaterButtonClicks = 3)
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnShowOnThirdClickButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .showRateNeverButton()
            .setMinimumLaunchTimes(3)
            .setMinimumDays(0)
            .setMinimumLaunchTimesToShowAgain(5)
            .setMinimumDaysToShowAgain(0)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnRatingThresholdButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setRatingThreshold(RatingThreshold.FOUR_AND_A_HALF)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnFullStarRatingButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setShowOnlyFullStars(true)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomConfigClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .setDebug(false)

    }

    @Composable
    private fun OnCustomTextsButtonClicked(onClose: () -> Unit) {
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
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCancelableButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setCancelable(true)
            .setDialogCancelListener {
                onClose.invoke()
                toastLiveData.postValue("Dialog was canceled.")
            }
            .showIfMeetsConditions()
    }

    @Composable
    private fun OnCustomThemeButtonClicked(onClose: () -> Unit) {
        AppRatingCompose.Builder(this)
            .setDebug(true)
            .setCustomTheme(R.style.AppTheme_CustomAlertDialog)
            .setDialogCancelListener {
                toastLiveData.postValue("Dialog was canceled.")
                onClose.invoke()
            }
            .showIfMeetsConditions()
    }

    companion object {
        // The livedata is used so that no context is given into the click listeners. (NotSerializableException)
        private val toastLiveData: MutableLiveData<String> = MutableLiveData()
    }
}


