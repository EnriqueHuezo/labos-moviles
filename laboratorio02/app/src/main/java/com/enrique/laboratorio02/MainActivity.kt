package com.enrique.laboratorio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var w : EditText
    private lateinit var h : EditText
    private lateinit var result : TextView
    private lateinit var status : TextView
    private lateinit var range : TextView
    private lateinit var calculate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()

        addListeners()
    }

    private fun bind() {
        w = findViewById(R.id.weight_edit_text)
        h = findViewById(R.id.height_edit_text)
        result = findViewById(R.id.result_text_view)
        status = findViewById(R.id.status_text_view)
        range = findViewById(R.id.range_text_view)
        calculate = findViewById(R.id.action_calculate_button)
    }

    private fun addListeners() {
        calculate.setOnClickListener {

            var weight = w.text.toString()
            var height = h.text.toString()

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN

            if (!validateInput(weight, height)) {
                return@setOnClickListener
            } else {
                if (calculateIBM(weight.toFloat(), height.toFloat()) in 30.0..100.0) {
                    result .text = (df.format(calculateIBM(weight.toFloat(), height.toFloat()))).toString()
                    status.text = "Obese"
                    status.setTextColor(getColor(R.color.obese))
                    range.text = "(Normal range is 18.5-24.9)"

                } else if (calculateIBM(weight.toFloat(), height.toFloat()) in 25.0..29.99) {
                    result .text = (df.format(calculateIBM(weight.toFloat(), height.toFloat()))).toString()
                    status.text = "Overweight"
                    status.setTextColor(getColor(R.color.over_weight))
                    range.text = "(Normal range is 18.5-24.9)"

                } else if (calculateIBM(weight.toFloat(), height.toFloat()) in 18.5..24.99) {
                    result .text = (df.format(calculateIBM(weight.toFloat(), height.toFloat()))).toString()
                    status.text = "Healthy"
                    status.setTextColor(getColor(R.color.normal_weight))
                    range.text = "Normal"

                } else if (calculateIBM(weight.toFloat(), height.toFloat()) in 1.0..18.5) {
                    result .text = (df.format(calculateIBM(weight.toFloat(), height.toFloat()))).toString()
                    status.text = "Underweight"
                    status.setTextColor(getColor(R.color.under_weight))
                    range.text = "(Normal range is 18.5-24.9)"
                }
            }
        }
    }

    private fun calculateIBM(weight: Float, height: Float) : Float {
        return weight / ((height/100) * (height/100))
    }

    private fun validateInput(weight: String?, height: String?): Boolean{
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false;
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false;
            }
            else -> true
        }
    }
}