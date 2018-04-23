package com.luigivampa92.yandextest.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.ui.base.BaseActivity

class AuthActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, AuthActivity::class.java)
    }

    override fun createFragment() = AuthFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.text_title_auth)
    }
}