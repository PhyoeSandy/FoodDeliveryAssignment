package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.adapters.CheckOutAdapter
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.CheckoutPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.CheckoutPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.CheckoutView
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.bottomsheet_checkout.view.*

class CheckoutActivity : BaseActivity(), CheckoutView {

    companion object {
        const val PARM_RESTAURANT_IMAGE = "PARM_RESTAURANT_IMAGE"
        const val PARM_RESTAURANT_NAME = "PARM_RESTAURANT_NAME"
        const val PARM_RESTAURANT_ADDRESS = "PARM_RESTAURANT_DESRIPTION"
        const val PARM_RESTAURANT_RATING = "PARM_RESTAURANT_RATING"

        fun newIntent(
            context: Context, restaurant_name: String?, restaurant_address: String?,
            restaurant_image: String?, restaurant_rating: String?
        ): Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(PARM_RESTAURANT_IMAGE, restaurant_image)
            intent.putExtra(PARM_RESTAURANT_NAME, restaurant_name)
            intent.putExtra(PARM_RESTAURANT_ADDRESS, restaurant_address)
            intent.putExtra(PARM_RESTAURANT_RATING, restaurant_rating)
            return intent
        }
    }

    private lateinit var mPresenter: CheckoutPresenter
    private lateinit var mAdapter: CheckOutAdapter
    private lateinit var mOrderList: List<FoodVO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setUpPresenter()
        initView()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun initView() {
        tvName.text = intent.getStringExtra(PARM_RESTAURANT_NAME).toString()
        tvType.text = intent.getStringExtra(PARM_RESTAURANT_ADDRESS).toString()
        tvRatings.text = intent.getStringExtra(PARM_RESTAURANT_RATING).toString()
        intent.getStringExtra(PARM_RESTAURANT_IMAGE)?.let {
            ivRestaurant.load(it.toUri())
        }
    }

    private fun setUpActionListener() {
        tvCheckout.setOnClickListener {
            if(mOrderList.isNotEmpty()) {
                showBottomSheetDialog()
            }else
            {
                Toast.makeText(this,"Empty Cart Item", Toast.LENGTH_LONG).show()
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.bottomsheet_checkout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        view.btnOrder.setOnClickListener {
            Toast.makeText(this, "Order Track Clicked", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
            //mPresenter.removeAllCartItem(orderList = mOrderList)
            this.onBackPressed()

        }
        view.tvSomething.setOnClickListener {
            Toast.makeText(this, "Order Cancel Clicked", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
        dialog.show()
    }

    private fun setUpRecyclerView() {
        rvOrders.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mAdapter = CheckOutAdapter(mPresenter)
        rvOrders.adapter = mAdapter

    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<CheckoutPresenterImpl, CheckoutView>()
        mPresenter.onUiReady(this)

    }

    override fun showOrderList(orderList: List<FoodVO>) {

    }
}