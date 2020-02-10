package com.example.kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlin.listener.ListenNavigateBack
import com.example.kotlin.listener.ListenShowChatBot
import com.example.kotlin.listener.OnClickCloseListener
import com.example.kotlin.screen.favorite.FavoriteFragment
import com.example.kotlin.screen.history.HistoryFragment
import com.example.kotlin.screen.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListenNavigateBack, OnClickCloseListener,
    ListenShowChatBot{
    override fun showChatBot() {
    }

    override fun OnClickBack() {
        super.onBackPressed()
    }

    override fun clickClose() {
        addFragment(HomeFragment.getInstance())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent?.getStringExtra("Message") == "Success"){
            initView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        addFragment(HomeFragment.getInstance())
        val onNavigationSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home -> {
                        addFragment(HomeFragment.getInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_favorite -> {
                        addFragment(FavoriteFragment.getInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_history -> {
                        addFragment(HistoryFragment.getInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationSelectedListener)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.frameContainer, fragment)
            .commit()
    }

    companion object {
        fun getInstance(context: Context) = Intent(context, MainActivity::class.java)

    }

}
