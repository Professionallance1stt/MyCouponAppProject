package com.example.mylogin

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*



class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        var coupList: MutableList<Coupons> = ArrayList()
        var catList: MutableList<String> = ArrayList()
        var line: String?
        val minput = InputStreamReader(assets.open("tessts.csv"))
        val reader = BufferedReader(minput)
        var displayData: String = ""
        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line!!.split(",")

            displayData =
                displayData + row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4] + "\t" + row[5] + "\t" + row[6] + "\t" + row[7] + "\t" + row[8] + "\t" + row[9] + "\t" + row[10] + "\t" + row[11] + "\t" + row[12] + "\t" + row[13] + "\t" + row[14] + "\t" + row[15] + "\t" + row[16] + "\t" + row[17] + "\n"
            val values = line!!.split(",").toTypedArray()
            for (index in 800 until values.size) {
                var coupon = Coupons()
                coupon.setCoupon(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4],
                    values[5],
                    values[6],
                    values[7],
                    values[8],
                    values[9],
                    values[10],
                    values[11],
                    values[12], values[13], values[14], values[15], values[16], values[17]
                )
                catList.add(values[7])
                coupList.add(coupon)


            }
        }
        for(coupon in 800 until coupList.size) {
            val ll_main = findViewById(R.id.ll_main_layout) as LinearLayout
            val button_dynamic = Button(this)
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button_dynamic.text = coupList[coupon].id
            button_dynamic.setTag(coupList[coupon].id)

            ll_main.addView(button_dynamic)

        }

    }
}