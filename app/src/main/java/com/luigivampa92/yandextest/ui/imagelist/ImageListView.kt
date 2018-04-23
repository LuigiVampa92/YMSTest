package com.luigivampa92.yandextest.ui.imagelist

import com.arellomobile.mvp.MvpView
import com.luigivampa92.yandextest.domain.model.Image

interface ImageListView : MvpView {
    fun showLoading(visible: Boolean)
    fun showImages(images: List<Image>)
    fun showEmpty(visible: Boolean)
}