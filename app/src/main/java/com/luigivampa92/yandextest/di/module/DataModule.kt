package com.luigivampa92.yandextest.di.module

import android.content.Context
import com.luigivampa92.yandextest.data.prefs.AuthStorage
import com.luigivampa92.yandextest.data.RestApi
import com.luigivampa92.yandextest.data.repository.YandexDiskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideYandexDiskRepository(restApi: RestApi) = YandexDiskRepository(restApi)

    @Provides
    @Singleton
    fun provideAuthStorage(context: Context) = AuthStorage(context)
}