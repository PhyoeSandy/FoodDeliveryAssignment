package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.adapters.FoodItemAdapter
import com.padcmyanmar.padcx.fooddeliveryassignment.adapters.PopularChoiceAdapter
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.RestaurantDetailPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.RestaurantDetailPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.RestaurantDetailView
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_restaurant_details.*

class RestaurantDetailsActivity : BaseActivity(), RestaurantDetailView {

    companion object {
        const val PARM_DOCUMENTID = "Document ID"
        fun newIntent(context: Context, documentId: String): Intent {
            val intent = Intent(context, RestaurantDetailsActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, documentId)
            return intent
        }
    }

    private lateinit var mPresenter: RestaurantDetailPresenter
    private lateinit var mPopularChoiceAdapter: PopularChoiceAdapter
    private lateinit var mFoodItemAdapter: FoodItemAdapter
    private lateinit var mRestaurantData: RestaurantVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        setupPresenter()
        setupRecyclerView()
        setupActionListener()
        mPresenter.onUiReady(this, intent.getStringExtra(PARM_DOCUMENTID).toString())
    }

    private fun setupActionListener() {

        btnAddTocart.setOnClickListener {
            mRestaurantData?.let {
                startActivity(
                    CheckoutActivity.newIntent(
                        this,
                        it?.name,
                        it?.address,
                        it?.image,
                        it?.ratings.toString()
                    )
                )
            }

        }
    }

    private fun setupRecyclerView() {
        mFoodItemAdapter = FoodItemAdapter(mPresenter)
        rvBurgerList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mFoodItemAdapter
        }

        mPopularChoiceAdapter = PopularChoiceAdapter()
        rvBurgers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mPopularChoiceAdapter
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<RestaurantDetailPresenterImpl, RestaurantDetailView>()
    }

    override fun showRestaurantData(restaurantVO: RestaurantVO) {
        mRestaurantData = restaurantVO
        tvRestaurantTitle.text = mRestaurantData.name
        tvType.text = mRestaurantData.type
        tvAddress.text = mRestaurantData.address
        restaurantVO.image?.let {
            ivItem.load(it.toUri())
        }
    }

    override fun showPopularChoicesFoodItem(popularFoodList: List<FoodVO>) {
        mPopularChoiceAdapter.setNewData(popularFoodList.toMutableList())
    }

    override fun showFoodItemList(foodList: List<FoodVO>) {
       mFoodItemAdapter.setNewData(foodList.toMutableList())
    }

    override fun showAddToCartButton(cartCount: Long) {
        if(cartCount > 0) btnAddTocart.visibility = View.VISIBLE
        else btnAddTocart.visibility = View.GONE
    }
}