package com.luigivampa92.yandextest.di.module.screen

import android.app.Activity
import com.luigivampa92.yandextest.di.scope.ActivityScope
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.routing.BindableRouting
import com.luigivampa92.yandextest.routing.screen.AuthScreenRouting
import com.luigivampa92.yandextest.ui.auth.AuthActivity
import com.luigivampa92.yandextest.ui.auth.AuthFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragmentInjector(): AuthFragment

    @Binds
    @ActivityScope
    abstract fun activity(activity: AuthActivity): Activity

    @Binds
    @ActivityScope
    abstract fun routing(routing: AuthScreenRouting): BindableRouting
}