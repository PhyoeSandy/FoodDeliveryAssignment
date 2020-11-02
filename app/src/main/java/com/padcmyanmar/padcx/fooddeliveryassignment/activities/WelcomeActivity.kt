package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.WelcomePresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.WelcomePresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.WelcomeView
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity(), WelcomeView {

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, WelcomeActivity::class.java)
        }
    }

    private lateinit var mPresenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setupPresenters()

        btnStart.setOnClickListener {
            mPresenter.onTapGettingStart()
        }
    }

    private fun setupPresenters() {
        mPresenter = getPresenter<WelcomePresenterImpl, WelcomeView>()
        mPresenter.onUiReady(this)
    }

    override fun navigateToIntroScreen() {
        startActivity(IntroductionActivity.newIntent(this))
    }
}