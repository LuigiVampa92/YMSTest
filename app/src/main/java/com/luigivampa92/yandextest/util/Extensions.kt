package com.luigivampa92.yandextest.util

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.YandexTestApplication

fun getString(@StringRes res: Int) = YandexTestApplication.INSTANCE.getString(res)

fun alertConformation(fragment: Fragment, text: String, action: () -> Unit) {
    fragment.activity?.let { alertConformation(it, text, action) }
}

fun alertConformation(fragment: Fragment, @StringRes text: Int, action: () -> Unit) {
    fragment.activity?.let { alertConformation(it, text, action) }
}

fun alertConformation(activity: Activity, text: String, action: () -> Unit) {
    AlertDialog.Builder(activity)
            .setCancelable(false)
            .setMessage(text)
            .setPositiveButton(R.string.text_yes, { _, _ -> action.invoke()})
            .setNegativeButton(R.string.text_no, { dialog, _ -> dialog.dismiss() })
            .show()
}

fun alertConformation(activity: Activity, @StringRes text: Int, action: () -> Unit) {
    AlertDialog.Builder(activity)
            .setCancelable(false)
            .setMessage(text)
            .setPositiveButton(R.string.text_yes, { _, _ -> action.invoke()})
            .setNegativeButton(R.string.text_no, { dialog, _ -> dialog.dismiss() })
            .show()
}