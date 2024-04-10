package com.example.reto9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val button: Button by lazy {
        findViewById<Button>(R.id.btn)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val e = findViewById<EditText>(R.id.edt)
        val a = findViewById<EditText>(R.id.edt2)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nombre", e.text.toString())
            intent.putExtra("apellido", a.text.toString())

            startActivity(intent)
        }
    }
}