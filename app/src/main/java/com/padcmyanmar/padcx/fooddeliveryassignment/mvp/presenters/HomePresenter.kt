package com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters

import com.padcmyanmar.padcx.fooddeliveryassignment.delegates.RestaurantItemDelegate
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 11/1/2020.
 */
interface HomePresenter : BasePresenter<HomeView>, RestaurantItemDelegate {

}