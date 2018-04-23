package com.luigivampa92.yandextest.ui.main

import android.os.Bundle
import android.view.Window
import com.luigivampa92.yandextest.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun createFragment() = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        actionBar?.hide()
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
    }
}
