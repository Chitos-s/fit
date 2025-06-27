package com.example.fit.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ImageButton
import com.example.fit.R
import com.google.android.material.button.MaterialButton

class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val button = findViewById<ImageButton>(R.id.back)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}