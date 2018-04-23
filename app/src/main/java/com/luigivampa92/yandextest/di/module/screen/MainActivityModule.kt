package com.luigivampa92.yandextest.di.module.screen

import android.app.Activity
import com.luigivampa92.yandextest.di.scope.ActivityScope
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.routing.BindableRouting
import com.luigivampa92.yandextest.routing.screen.MainScreenRouting
import com.luigivampa92.yandextest.ui.main.MainActivity
import com.luigivampa92.yandextest.ui.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragmentInjector(): MainFragment

    @Binds
    @ActivityScope
    abstract fun activity(activity: MainActivity): Activity

    @Binds
    @ActivityScope
    abstract fun routing(routing: MainScreenRouting): BindableRouting
}