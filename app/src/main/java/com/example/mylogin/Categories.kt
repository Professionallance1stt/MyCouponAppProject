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
        val coupList: MutableList<Coupons> = ArrayList()
        val catList: MutableList<String> = ArrayList()
        var line: String?
        val minput = InputStreamReader(assets.open("tessts.csv"))
        val reader = BufferedReader(minput)
        var displayData = ""
        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line!!.split(",")
            var number = 0
            //Creates a row system
            displayData =
                displayData + row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3] + "\t" + row[4] + "\t" + row[5] + "\t" + row[6] + "\t" + row[7] + "\t" + row[8] + "\t" + row[9] + "\t" + row[10] + "\t" + row[11] + "\t" + row[12] + "\t" + row[13] + "\t" + row[14] + "\t" + row[15] + "\t" + row[16] + "\t" + row[17] + "\n"
            val values = line!!.split(",").toTypedArray()
            for (index in values) {
//Rows changed to input into the list.
                val coupon = Coupons()
                coupon.setCoupon(
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    row[9],
                    row[10],
                    row[11],
                    row[12], row[13], row[14], row[15], row[16], row[17]
                )
                //due to bug it will duplicate it 10 times over, with this it does not
                number += 1
                if (number % 10 == 0) {
                    catList.add(row[8])
                    coupList.add(coupon)
                }

            }
        }
        //Adds the buttons to the layout
        for (coupon in 0 until coupList.size) {
            val llmain = findViewById<LinearLayout>(R.id.ll_main_layout)
            val buttondynamic = Button(this)
            buttondynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            buttondynamic.text = coupList[coupon].id
            buttondynamic.tag = coupList[coupon].id

            llmain.addView(buttondynamic)
        }

    }
}


