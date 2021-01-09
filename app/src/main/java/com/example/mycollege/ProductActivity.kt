package com.example.mycollege

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val images = listOf<Int>(
                R.drawable.t,
                R.drawable.tt,
                R.drawable.ttt
        )
        val adapter =ProductMediaViewPager(images)
        vpProductsMedia.adapter =adapter
        vpCircleIndicator.setViewPager(vpProductsMedia)
    }
}