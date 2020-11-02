package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.adapters.ViewPagerTwoAdapter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.IntroPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.IntroPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.IntroView
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_introduction.*

class IntroductionActivity : BaseActivity(), IntroView {
    private lateinit var mPresenter: IntroPresenter
    private var viewPagerTwoAdapter : ViewPagerTwoAdapter? = null

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, IntroductionActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        setUpPresenter()
        setUpActionListeners()
        setupViewPager()

    }

    private fun setupViewPager() {
        viewPagerTwoAdapter = ViewPagerTwoAdapter(this)
        introViewPager.adapter = viewPagerTwoAdapter
        TabLayoutMediator(tabLayout,introViewPager){tab,position->{

        }}.attach()
    }

    private fun setUpActionListeners() {
        btnCreateAccount.setOnClickListener {
            mPresenter.onTapCreateAccount()
        }

        tvLogin.setOnClickListener {
            mPresenter.onTapLogin()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<IntroPresenterImpl, IntroView>()
        mPresenter.onUiReady(this)
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}