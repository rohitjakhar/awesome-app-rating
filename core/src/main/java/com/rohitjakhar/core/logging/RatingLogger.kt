package com.rohitjakhar.core.logging

import android.util.Log

object RatingLogger {
    private const val TAG = "awesome_app_rating"
    var isLoggingEnabled = true

    fun verbose(logMessage: String) {
        if (isLoggingEnabled) Log.v(TAG, logMessage)
    }

    fun debug(logMessage: String) {
        try {
            if (isLoggingEnabled) Log.d(TAG, logMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun info(logMessage: String) {
        if (isLoggingEnabled) Log.i(TAG, logMessage)
    }

    fun warn(logMessage: String) {
        if (isLoggingEnabled) Log.w(TAG, logMessage)
    }

    fun error(logMessage: String) {
        if (isLoggingEnabled) Log.e(TAG, logMessage)
    }
}
