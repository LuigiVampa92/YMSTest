package com.luigivampa92.yandextest.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.routing.BindableRouting
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = fragmentInjector

    @Inject
    protected lateinit var router: Router
    @Inject
    protected lateinit var routing: BindableRouting

    @IdRes
    private var fragmentContainerRes = R.id.fragment_container
    @LayoutRes
    open fun layoutRes() = R.layout.activity_base

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())

        val fragmentManager = supportFragmentManager
        val fragment: Fragment? = fragmentManager.findFragmentById(fragmentContainerRes)
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(fragmentContainerRes, createFragment())
                    .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        routing.bind()
    }

    override fun onPause() {
        super.onPause()
        routing.unbind()
    }
}