package com.luigivampa92.yandextest.routing.screen

import com.luigivampa92.yandextest.routing.BaseRouting
import com.luigivampa92.yandextest.routing.Screens
import com.luigivampa92.yandextest.ui.auth.AuthActivity
import com.luigivampa92.yandextest.ui.imagelist.ImageListActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.SystemMessage
import javax.inject.Inject

class AuthScreenRouting @Inject constructor (
        activity: AuthActivity,
        navigatorHolder: NavigatorHolder
) : BaseRouting<AuthActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        when (command) {
            is Forward -> {
                when (command.screenKey) {
                    Screens.IMAGE_LIST -> {
                        openActivity(ImageListActivity.newIntent(activity))
                    }
                }
            }
            is SystemMessage -> {
                showErrorAlert(command.message)
            }
        }
    }
}