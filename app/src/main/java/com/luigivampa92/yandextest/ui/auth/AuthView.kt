package com.luigivampa92.yandextest.ui.auth

import com.arellomobile.mvp.MvpView

interface AuthView : MvpView {
    fun enableButton(enable: Boolean)
    fun callLoginBySdk()
}