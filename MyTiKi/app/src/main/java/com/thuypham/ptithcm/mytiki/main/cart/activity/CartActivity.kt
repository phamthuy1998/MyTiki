package com.thuypham.ptithcm.mytiki.main.cart.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.thuypham.ptithcm.mytiki.R

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

    fun onClickQuiteCart(view: View) {finish()}
}
