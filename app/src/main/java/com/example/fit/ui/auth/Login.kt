package com.example.fit.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ImageButton
import com.example.fit.FitMain
import com.example.fit.R
import com.google.android.material.button.MaterialButton

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val button1 = findViewById<ImageButton>(R.id.back)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<MaterialButton>(R.id.button)
        button2.setOnClickListener {
            val intent = Intent(this, FitMain::class.java)
            startActivity(intent)
        }
    }
}