package com.luigivampa92.yandextest.routing.screen

import com.luigivampa92.yandextest.routing.BaseRouting
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.ui.auth.AuthActivity
import com.luigivampa92.yandextest.ui.imagelist.ImageListActivity
import com.luigivampa92.yandextest.ui.main.MainActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.SystemMessage
import javax.inject.Inject

class MainScreenRouting @Inject constructor (
        activity: MainActivity,
        navigatorHolder: NavigatorHolder
): BaseRouting<MainActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        when (command) {
            is Forward -> when (command.screenKey) {
                Screens.AUTH -> {
                    openActivityNewRoot(AuthActivity.newIntent(activity))
                }
                Screens.IMAGE_LIST -> {
                    openActivityNewRoot(ImageListActivity.newIntent(activity))
                }
            }
            is SystemMessage -> {
                showToast(command.message)
            }
        }
    }
}