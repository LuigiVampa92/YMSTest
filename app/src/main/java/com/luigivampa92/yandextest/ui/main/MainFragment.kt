package com.luigivampa92.yandextest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.luigivampa92.yandextest.domain.presenter.MainPresenter
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.ui.base.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment(), MainView {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_main, container, false)
}