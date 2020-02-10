package com.example.kotlin.screen.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.R
import com.example.kotlin.model.Lego

class DetailLegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_lego)
        val lego = intent.getParcelableExtra<Lego>("lego")
        addFragment(lego)
    }

    private fun addFragment(lego: Lego?) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameDetailContainer, DetailLegoFragment.getInstance(lego!!))
            .commit()
    }

    companion object {
        fun getIntent(context: Context, lego: Lego): Intent {
            val intent = Intent(context, DetailLegoActivity::class.java)
            intent.putExtra("lego", lego)
            return intent
        }
    }

}
