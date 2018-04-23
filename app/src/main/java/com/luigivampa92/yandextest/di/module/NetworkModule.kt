package com.luigivampa92.yandextest.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luigivampa92.yandextest.data.AuthInterceptor
import com.luigivampa92.yandextest.util.URL
import com.luigivampa92.yandextest.YandexTestApplication
import com.luigivampa92.yandextest.data.RestApi
import com.luigivampa92.yandextest.util.DateAdapter
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateAdapter())
            .create()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(
            httpClient: OkHttpClient,
            callAdapterFactory: CallAdapter.Factory,
            converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
            .baseUrl(URL.BASE_API_URL)
            .client(httpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit) = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun providePicasso(httpClient: OkHttpClient): Picasso = Picasso.Builder(YandexTestApplication.INSTANCE)
            .downloader(OkHttp3Downloader(httpClient))
            .loggingEnabled(false)
            .indicatorsEnabled(false)
            .build()
}