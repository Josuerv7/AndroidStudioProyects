package com.example.reto8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class SingUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val btn = findViewById<Button>(R.id.btn2)

        btn.setOnClickListener {
            finish()
        }
    }
}