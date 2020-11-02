package com.padcmyanmar.padcx.fooddeliveryassignment.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.AccountFragment
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.OfferFragment
import com.padcmyanmar.padcx.fooddeliveryassignment.fragments.RestaurantFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, RestaurantFragment.newInstance("a","b"))
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener(
            object : BottomNavigationView.OnNavigationItemSelectedListener{

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when(item.itemId){
                        R.id.action_home -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,RestaurantFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                        R.id.action_offer -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,OfferFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                        R.id.action_account -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.flContainer,AccountFragment.newInstance("a","b"))
                                .commit()
                            return true
                        }
                    }
                    return false
                }
            })

    }
}