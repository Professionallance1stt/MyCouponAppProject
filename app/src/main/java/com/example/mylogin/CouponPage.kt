package com.example.mylogin

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CouponPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_page)

        var coupLists = CouponList()
        coupLists.coupList

        val couponName = findViewById<TextView>(R.id.txtCoupon)
        couponName.text = intent.getStringExtra("Title")
        val expiration = findViewById<TextView>(R.id.textExp)
        expiration.text = intent.getStringExtra("Expiration")
        val desc = findViewById<TextView>(R.id.textDesc)
        desc.text =  intent.getStringExtra("Description")
        val barCode = findViewById<ImageView>(R.id.barCode)

    }



}