package com.luigivampa92.yandextest.data.prefs

import android.annotation.SuppressLint
import android.content.Context

abstract class AbstractSharedPreferences(private val context: Context, private val filename: String) {

    protected fun getString(key: String): String? {
        val preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        return preferences.getString(key, null)
    }

    @SuppressLint("ApplySharedPref")
    protected fun putString(key: String, value: String) {
        val preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        preferences.edit()
                .putString(key, value)
                .commit()
    }

    @SuppressLint("ApplySharedPref")
    protected fun clear() {
        val preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        preferences.edit()
                .clear()
                .commit()
    }
}