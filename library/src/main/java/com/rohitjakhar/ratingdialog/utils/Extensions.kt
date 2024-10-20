package com.rohitjakhar.ratingdialog.utils

import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import android.widget.TextView
import androidx.annotation.StringRes

fun AlertDialog.Builder.setPositiveButton(@StringRes textId: Int? = null, text: String? = null, listener: DialogInterface.OnClickListener) {
    textId?.let {
        setPositiveButton(it, listener)
    }
    text?.let {
        setPositiveButton(text,listener)
    }
}

fun AlertDialog.Builder.setNegativeButton(@StringRes textId: Int? = null, text: String? = null, listener: DialogInterface.OnClickListener) {
    textId?.let {
        setNegativeButton(it, listener)
    }
    text?.let {
        setNegativeButton(text,listener)
    }
}

fun AlertDialog.Builder.setTitle(@StringRes textId: Int? = null, text: String?) {
    textId?.let {
        setTitle(it)
    }
    text?.let {
        setTitle(it)
    }
}

fun AlertDialog.Builder.setMessage(@StringRes textId: Int? = null, text: String?) {
    textId?.let {
        setMessage(it)
    }
    text?.let {
        setMessage(it)
    }
}

fun TextView.setText(@StringRes textId: Int? = null, text: String?) {
    textId?.let {
        setText(it)
    }
    text?.let {
        setText(it)
    }
}

fun TextView.setHint(@StringRes textId: Int? = null, text: String?) {
    textId?.let {
        setHint(it)
    }
    text?.let {
        setHint(it)
    }
}
