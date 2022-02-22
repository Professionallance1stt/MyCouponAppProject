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
        coupLists = coupLists.getInstance()!!
        val couponName = findViewById<TextView>(R.id.txtCoupon)
        couponName.text = coupLists.coupList[coupLists.indexer!!].title +" " +  coupLists.coupList[coupLists.indexer!!].values
        val expiration = findViewById<TextView>(R.id.textExp)
        couponName.text = coupLists.coupList[coupLists.indexer!!].enddate
        val desc = findViewById<TextView>(R.id.textDesc)
        couponName.text = coupLists.coupList[coupLists.indexer!!].desc
        val barCode = findViewById<ImageView>(R.id.barCode)

    }
}