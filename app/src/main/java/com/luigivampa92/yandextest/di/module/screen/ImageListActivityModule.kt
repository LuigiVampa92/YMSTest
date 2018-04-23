package com.luigivampa92.yandextest.di.module.screen

import android.app.Activity
import com.luigivampa92.yandextest.di.scope.ActivityScope
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.routing.BindableRouting
import com.luigivampa92.yandextest.routing.screen.ImageListScreenRouting
import com.luigivampa92.yandextest.ui.imagelist.ImageListActivity
import com.luigivampa92.yandextest.ui.imagelist.ImageListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ImageListActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragmentInjector(): ImageListFragment

    @Binds
    @ActivityScope
    abstract fun activity(activity: ImageListActivity): Activity

    @Binds
    @ActivityScope
    abstract fun routing(routing: ImageListScreenRouting): BindableRouting
}