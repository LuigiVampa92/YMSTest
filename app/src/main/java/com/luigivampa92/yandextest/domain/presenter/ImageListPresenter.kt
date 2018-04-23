package com.luigivampa92.yandextest.domain.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yandextest.data.prefs.AuthStorage
import com.luigivampa92.yandextest.data.repository.YandexDiskRepository
import com.luigivampa92.yandextest.di.scope.FragmentScope
import com.luigivampa92.yandextest.domain.model.Image
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.routing.transition.ZoomedImageTransitionData
import com.luigivampa92.yandextest.ui.imagelist.ImageListView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@FragmentScope
@InjectViewState
class ImageListPresenter @Inject constructor (
        private val router: Router,
        private val yandexDiskRepository: YandexDiskRepository,
        private val authStorage: AuthStorage
) : MvpPresenter<ImageListView>() {

    fun loadImages(limit: Int, offset: Int) {
        if (offset == 0) {
            viewState.showLoading(true)
        }
        yandexDiskRepository.getImages(limit, offset).subscribe(
                        {
                            viewState.showLoading(false)
                            viewState.showImages(it)
                        },
                        {
                            viewState.showLoading(false)
                            router.showSystemMessage(it.message)
                        }
                )
    }

    fun imageClicked(image: Image) {
        router.navigateTo(Screens.IMAGE_ZOOMED, ZoomedImageTransitionData(image.name, image.file))
    }

    fun deauth() {
        authStorage.deleteToken()
        router.navigateTo(Screens.AUTH)
    }
}