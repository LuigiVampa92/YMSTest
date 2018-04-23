package com.luigivampa92.yandextest.routing

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.ui.base.BaseActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command

abstract class BaseRouting<out T: BaseActivity> constructor (
        protected val activity: T,
        private val navigatorHolder: NavigatorHolder
) : BindableRouting {

    private val fragmentManager by lazy { activity.supportFragmentManager }

    protected val currentFragment: Fragment?
        get() = fragmentManager.fragments.firstOrNull()

    private val navigator = Navigator {
        recognizeCommand(it)
    }

    override fun bind() {
        navigatorHolder.setNavigator(navigator)
    }


    override fun unbind() {
        navigatorHolder.removeNavigator()
    }

    protected open fun recognizeCommand(command: Command) {}

    protected fun openActivity(intent: Intent) {
        activity.startActivity(intent)
    }

    protected fun openActivityNewRoot(intent: Intent) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
        activity.finish()
    }

    protected fun finishActivity() {
        activity.finish()
    }

    protected fun openFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    protected fun popFragment() {
        if (fragmentManager.fragments.count() == 0) {
            activity.finish()
        } else {
            fragmentManager.popBackStack()
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    protected fun showErrorAlert(message: String) {
        AlertDialog.Builder(activity)
                .setTitle(R.string.text_error)
                .setMessage(message)
                .setPositiveButton(R.string.text_ok, { dialog, _ -> dialog.dismiss() })
                .show()
    }
}