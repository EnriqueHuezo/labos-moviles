package com.example.labo03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var fiveCents: ImageView
    private lateinit var tenCents: ImageView
    private lateinit var quarter: ImageView
    private lateinit var oneDollar: ImageView
    private lateinit var moneyCount: TextView

    private var total = 0.0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
        addLIsteners()

    }

    private fun bind() {
        fiveCents = findViewById(R.id.five_cents_image_view)
        tenCents = findViewById(R.id.ten_cents_image_view)
        quarter = findViewById(R.id.quarter_image_view)
        oneDollar = findViewById(R.id.one_dollar_image_view)
        moneyCount = findViewById(R.id.money_text_view)
    }

    private fun addLIsteners() {
        fiveCents.setOnClickListener {
            total += 0.05
            moneyCount.text = "$${pattern(total)}"
        }

        tenCents.setOnClickListener {
            total += 0.10
            moneyCount.text = "$${pattern(total)}"
        }

        quarter.setOnClickListener {
            total += 0.25
            moneyCount.text = "$${pattern(total)}"
        }

        oneDollar.setOnClickListener {
            total += 1.0
            moneyCount.text = "$${pattern(total)}"
        }
    }

    private fun pattern(number: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        return df.format(number)
    }

}