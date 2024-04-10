package com.example.preferences1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et1=findViewById<EditText>(R.id.et1)
        val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
        et1.setText(preferencias.getString("mail", ""))
        val boton1=findViewById<Button>(R.id.boton1)
        boton1.setOnClickListener {
            val editor = preferencias.edit()
            editor.putString("mail", et1.text.toString())
            editor.commit()
            finish()
        }
    }
}