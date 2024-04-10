package com.example.reto9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val l = findViewById<TextView>(R.id.txv);
        val secreto = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        l.text = "Bienvenido: $secreto  $apellido"
    }
}