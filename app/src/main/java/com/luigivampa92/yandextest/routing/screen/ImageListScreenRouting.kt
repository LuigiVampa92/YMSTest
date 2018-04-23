package com.luigivampa92.yandextest.routing.screen

import com.luigivampa92.yandextest.routing.BaseRouting
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.routing.transition.ZoomedImageTransitionData
import com.luigivampa92.yandextest.ui.auth.AuthActivity
import com.luigivampa92.yandextest.ui.imagelist.ZoomedImageFragment
import com.luigivampa92.yandextest.ui.imagelist.ImageListActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.SystemMessage
import javax.inject.Inject

class ImageListScreenRouting @Inject constructor (
        activity: ImageListActivity,
        navigatorHolder: NavigatorHolder
) : BaseRouting<ImageListActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        when (command) {
            is Forward -> {
                when (command.screenKey) {
                    Screens.IMAGE_ZOOMED -> {
                        when (command.transitionData) {
                            is ZoomedImageTransitionData -> {
                                val data = command.transitionData as ZoomedImageTransitionData
                                openFragment(ZoomedImageFragment.newInstance(data))
                            }
                        }
                    }
                    Screens.AUTH -> {
                        openActivityNewRoot(AuthActivity.newIntent(activity))
                    }
                }
            }
            is SystemMessage -> {
                showErrorAlert(command.message)
            }
        }
    }
}