package com.luigivampa92.yandextest

import android.app.Activity
import android.app.Application
import com.luigivampa92.yandextest.di.component.AppComponent
import com.luigivampa92.yandextest.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class YandexTestApplication : Application(), HasActivityInjector {

    companion object {
        lateinit var INSTANCE: YandexTestApplication
    }

    @Inject
    protected lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder()
                .withContext(this)
                .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun getAppComponent() = appComponent
}