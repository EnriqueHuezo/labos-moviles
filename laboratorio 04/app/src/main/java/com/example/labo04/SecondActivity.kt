package com.example.labo04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var nameText: TextView
    private lateinit var emailText: TextView
    private lateinit var numberText: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bind()

        val name = intent.getStringExtra("name").toString()
        val email = intent.getStringExtra("email").toString()
        val number = intent.getStringExtra("number").toString()

        nameText.text = "Nombre ${name}"
        emailText.text = "Correo electronico ${email}"
        numberText.text = "Numero de telefono ${number}"

        addListeners()

    }

    private fun bind() {
        nameText = findViewById(R.id.name_text_view)
        emailText = findViewById(R.id.email_text_view)
        numberText = findViewById(R.id.number_text_view)
        button = findViewById(R.id.action_share)
    }

    private fun addListeners() {
        button.setOnClickListener {
            val name = intent.getStringExtra("name").toString()
            val email = intent.getStringExtra("email").toString()
            val number = intent.getStringExtra("number").toString()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Nombre: ${name} \nCorreo electronico ${email}\nNumero de telefono: ${number}")

                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

}