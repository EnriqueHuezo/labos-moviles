package com.example.labo04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextName: TextInputEditText
    private lateinit var inputTextEmail: TextInputEditText
    private lateinit var inputTextNumber: TextInputEditText
    private lateinit var button: Button

    var name = ""
    var email = ""
    var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()
        addListeners()
    }

    private fun bind() {
        inputTextName = findViewById(R.id.name_text_input_edit_text)
        inputTextEmail = findViewById(R.id.email_text_input_edit_text)
        inputTextNumber = findViewById(R.id.number_text_input_edit_text)
        button = findViewById(R.id.button_action)
    }

    private fun addListeners() {
        button.setOnClickListener {
            name = inputTextName.text.toString()
            email = inputTextEmail.text.toString()
            number = inputTextNumber.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("number", number)
            startActivity(intent)
        }
    }

}