package com.example.lanzaractividad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AcercaDe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de)

        val botonsalir =findViewById<Button>(R.id.botonsalir)
        botonsalir.setOnClickListener{
            finish()
        }
    }
}