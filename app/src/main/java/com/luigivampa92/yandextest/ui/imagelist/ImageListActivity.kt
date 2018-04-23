package com.luigivampa92.yandextest.ui.imagelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.ui.base.BaseActivity

class ImageListActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, ImageListActivity::class.java)
    }

    override fun createFragment() = ImageListFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.text_title_image_list)
    }
}