package com.luigivampa92.yandextest.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.domain.presenter.AuthPresenter
import com.luigivampa92.yandextest.ui.base.BaseFragment
import com.yandex.authsdk.*
import javax.inject.Inject

class AuthFragment : BaseFragment(), AuthView {

    companion object {
        const val REQUEST_LOGIN_SDK = 8719
        fun newInstance() = AuthFragment()
    }

    private lateinit var unbinder: Unbinder
    @BindView(R.id.button_enter)
    protected lateinit var buttonEnter: Button

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var sdk: YandexAuthSdk

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_auth, container, false).also {
                unbinder = ButterKnife.bind(this, it)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        enableButton(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LOGIN_SDK) {
            try {
                val token = sdk.extractToken(resultCode, data)
                token?.let {
                    presenter.tokenReceived(it)
                }
            }
            catch (e: YandexAuthException) {
                presenter.authError(e)
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun enableButton(enable: Boolean) {
        buttonEnter.isEnabled = enable
    }

    override fun callLoginBySdk() {
        sdk = YandexAuthSdk(context!!, YandexAuthOptions(context!!, true))
        val intent = sdk.createLoginIntent(context!!, null)
        startActivityForResult(intent, REQUEST_LOGIN_SDK)
    }

    @OnClick(R.id.button_enter)
    protected fun buttonEnterClicked() {
        presenter.enter()
    }
}