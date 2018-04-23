package com.luigivampa92.yandextest.data.prefs

import android.content.Context
import javax.inject.Inject

class AuthStorage @Inject constructor(context: Context) : AbstractSharedPreferences(context, "auth_yandex") {

    companion object {
        private const val KEY_TOKEN = "token"
    }

    fun hasToken(): Boolean {
        val value: String? = getString(KEY_TOKEN)
        return !value.isNullOrEmpty()
    }

    fun getToken() = getString("token")

    fun saveToken(token: String) {
        putString(KEY_TOKEN, token)
    }

    fun deleteToken() {
        clear()
    }
}