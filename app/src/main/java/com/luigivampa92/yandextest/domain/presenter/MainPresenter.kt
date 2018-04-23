package com.luigivampa92.yandextest.domain.presenter

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yandextest.data.AuthInterceptor
import com.luigivampa92.yandextest.data.prefs.AuthStorage
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.ui.main.MainView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@FragmentScope
@InjectViewState
class MainPresenter @Inject constructor (
        private val router: Router,
        private val authStorage: AuthStorage,
        private val authInterceptor: AuthInterceptor
) : MvpPresenter<MainView>() {

    companion object {
        private const val splashDelay = 500L
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Handler().postDelayed({
            if (authStorage.hasToken()) {
                val token = authStorage.getToken()
                authInterceptor.setToken(token!!)
                router.navigateTo(Screens.IMAGE_LIST)
            }
            else {
                router.navigateTo(Screens.AUTH)
            }
        }, splashDelay)
    }
}