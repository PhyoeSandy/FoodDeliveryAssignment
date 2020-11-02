package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.LoginPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.LoginPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.LoginView
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupPresenter()
        setupListeners()
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(
                etEmail.text.toString().trim(), etPassword.text.toString().trim()
            )
        }

        tvLoginAcc.setOnClickListener {
            mPresenter.onTapSignup()
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
        mPresenter.onUiReady(this)
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}