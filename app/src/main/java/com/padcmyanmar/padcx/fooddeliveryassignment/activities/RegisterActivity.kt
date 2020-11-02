package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.RegisterPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.RegisterPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RegisterView
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupPresenter()
        setupListeners()

    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin()
        }

        btnCreateAcc.setOnClickListener {
            mPresenter.onTapCreateAccount(
                etName.text.toString().trim(), etEmail.text.toString().trim(),
                etPassword.text.toString().trim(), etPhone.text.toString().trim())
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
        mPresenter.onUiReady(this)

    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }
}