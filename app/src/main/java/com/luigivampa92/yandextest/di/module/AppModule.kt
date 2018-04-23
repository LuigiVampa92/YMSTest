package com.luigivampa92.yandextest.di.module

import com.luigivampa92.yandextest.di.module.screen.AuthActivityModule
import com.luigivampa92.yandextest.di.module.screen.ImageListActivityModule
import com.luigivampa92.yandextest.di.module.screen.MainActivityModule
import com.luigivampa92.yandextest.di.scope.ActivityScope
import com.luigivampa92.yandextest.ui.auth.AuthActivity
import com.luigivampa92.yandextest.ui.imagelist.ImageListActivity
import com.luigivampa92.yandextest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = arrayOf(AndroidSupportInjectionModule::class))
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ImageListActivityModule::class))
    abstract fun imageListActivityInjector(): ImageListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(AuthActivityModule::class))
    abstract fun authActivityInjector(): AuthActivity
}