package com.padcmyanmar.padcx.fooddeliveryassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.activities.RestaurantDetailsActivity
import com.padcmyanmar.padcx.fooddeliveryassignment.adapters.*
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.FoodVO
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.RestaurantVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.HomePresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_restaurant.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RestaurantFragment : BaseFragment(), HomeView {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: HomePresenter

    private lateinit var mRestaurantAdapter: RestaurantAdapter
    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mPopularChoiceAdapter: PopularChoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupRecyclerView()
        mPresenter.onUiReady(this)
    }

    private fun setupRecyclerView() {
        mCategoryAdapter = CategoryAdapter()
        mPopularChoiceAdapter = PopularChoiceAdapter()
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<HomePresenterImpl, HomeView>()
    }

    override fun showCategories(categoryList: List<CategoryVO>) {
        mCategoryAdapter.setNewData(categoryList.toMutableList())
    }

    override fun showRestaurants(restaurantList: List<RestaurantVO>) {
        mRestaurantAdapter.setNewData(restaurantList.toMutableList())
    }

    override fun showPopularChoices(popularList: List<FoodVO>) {
        mPopularChoiceAdapter.setNewData(popularList.toMutableList())
    }

    override fun navigateToRestaurantDetailsScreen(documentId: String) {
        startActivity(
            activity?.applicationContext?.let {
                RestaurantDetailsActivity.newIntent(it, documentId)
            })
    }

    override fun changeHomeScreenViewType(viewType: Int) {
        mRestaurantAdapter = RestaurantAdapter(mPresenter, viewType)
        when (viewType) {
            0 -> {
                viewTypeOne(viewType)
            }
            1 -> {
                viewTypeTwo(viewType)
            }
        }
    }

    private fun viewTypeOne(viewType: Int) {
        rvCategory.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = mCategoryAdapter
        }

        rvHomeTwo.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mRestaurantAdapter
        }
    }

    private fun viewTypeTwo(viewType: Int) {
        deliveryTo.visibility = View.GONE
        spinnerBar.visibility = View.GONE
        rvCategory.visibility = View.GONE
        tvPopularChoice.visibility = View.VISIBLE
        tvNewRestaurant.visibility = View.VISIBLE

        rvPopularChoice.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = mPopularChoiceAdapter
        }

        rvHomeTwo.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mRestaurantAdapter
        }
    }

}