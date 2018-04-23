package com.luigivampa92.yandextest.di.component

import android.content.Context
import com.luigivampa92.yandextest.ui.imagelist.ImageViewHolder
import com.luigivampa92.yandextest.YandexTestApplication
import com.luigivampa92.yandextest.di.module.AppModule
import com.luigivampa92.yandextest.di.module.NetworkModule
import com.luigivampa92.yandextest.di.module.RouterModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        RouterModule::class,
        NetworkModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: YandexTestApplication)

    fun inject(viewHolder: ImageViewHolder)
}