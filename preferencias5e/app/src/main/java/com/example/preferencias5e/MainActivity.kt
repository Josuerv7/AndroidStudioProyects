package com.example.preferencias5e

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et1=findViewById<EditText>(R.id.et1)
        val et2=findViewById<EditText>(R.id.et2)
        val preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE)
        val boton1=findViewById<Button>(R.id.boton1)
        boton1.setOnClickListener{
            val editor = preferencias.edit()
            editor.putString(et1.text.toString(), et2.text.toString())
            editor.commit()
            Toast.makeText(this, "Datos grabados", Toast.LENGTH_LONG).show()
            et1.setText("")
            et2.setText("")
        }
        val boton2=findViewById<Button>(R.id.boton2)
        boton2.setOnClickListener {
            val datos = preferencias.getString(et1.text.toString(), "")
            if (datos != null) {
                if (datos.length == 0)
                    Toast.makeText(this, "No existe dicho nombre en la agenda", Toast.LENGTH_LONG).show()
                else
                    et2.setText(datos)
            }
        }
    }
}