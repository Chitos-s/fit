package com.example.fit.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.content.Intent
import com.example.fit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_screen)

        val button1 = findViewById<MaterialButton>(R.id.button)
        button1.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<MaterialButton>(R.id.text_button)
        button2.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
