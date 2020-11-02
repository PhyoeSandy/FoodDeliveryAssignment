package com.padcmyanmar.padcx.shared.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun showErrorMessage(error: String) {
        // Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }

    inline fun <reified T : AbstractBasePresenter<W>, reified W : BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }
}