package com.luigivampa92.yandextest.domain.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.data.AuthInterceptor
import com.luigivampa92.yandextest.data.prefs.AuthStorage
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.util.getString
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.ui.auth.AuthView
import com.yandex.authsdk.YandexAuthException
import com.yandex.authsdk.YandexAuthToken
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@FragmentScope
@InjectViewState
class AuthPresenter @Inject constructor (
        private val router: Router,
        private val authStorage: AuthStorage,
        private val authInterceptor: AuthInterceptor
) : MvpPresenter<AuthView>() {

    fun enter() {
        viewState.enableButton(false)
        if (authStorage.hasToken()) {
            moveToImageListScreen()
        }
        else {
            viewState.callLoginBySdk()
        }
    }

    fun tokenReceived(token: YandexAuthToken) {
        authStorage.saveToken(token.value)
        moveToImageListScreen()
        viewState.enableButton(true)
    }

    fun authError(e: YandexAuthException) {
        val message = e.message.toString()
        router.showSystemMessage(when (message) {
            "[access_denied]" -> {
                getString(R.string.text_access_not_granted)
            }
            else -> { message }
        })
        viewState.enableButton(true)
    }

    private fun moveToImageListScreen() {
        val token = authStorage.getToken()
        authInterceptor.setToken(token!!)
        router.navigateTo(Screens.IMAGE_LIST)
    }
}