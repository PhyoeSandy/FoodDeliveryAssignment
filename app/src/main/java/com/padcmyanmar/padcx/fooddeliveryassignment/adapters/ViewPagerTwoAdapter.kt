package com.padcmyanmar.padcx.fooddeliveryassignment.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.IntroOneFragment
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.IntroThreeFragment
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.IntroTwoFragment

const val PAGE_COUNT = 3

class ViewPagerTwoAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                IntroOneFragment.newInstance()
            }
            1 -> {
                IntroTwoFragment.newInstance()
            }
            2 -> {
                IntroThreeFragment.newInstance()
            }
            else -> {
                IntroOneFragment.newInstance()
            }
        }
    }

}