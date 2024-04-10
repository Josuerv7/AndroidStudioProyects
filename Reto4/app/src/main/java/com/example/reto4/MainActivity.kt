package com.example.reto4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et1=findViewById<EditText>(R.id.et1)
        val et2=findViewById<EditText>(R.id.et2)
        val tv1=findViewById<TextView>(R.id.tv1)
        val button=findViewById<Button>(R.id.button)
        val spinner=findViewById<Spinner>(R.id.spinner)
        val lista = arrayOf("Estados Unidos", "Mexico", "Alemania", "Japon")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1
        button.setOnClickListener {
            try{
                when (spinner.selectedItem.toString()) {
                    "Estados Unidos" -> tv1.text = "Bienvenido ${et1.text.toString()} " +  "${et2.text.toString()}\n" + " Nacionalidad: Estados Unidos"
                    "Mexico" -> tv1.text = "Bienvenido ${et1.text.toString()} " +  "${et2.text.toString()}\n" + " Nacionalidad: Mexico"
                    "Alemania" -> tv1.text = "Bienvenido ${et1.text.toString()} " +  "${et2.text.toString()}\n" + " Nacionalidad: Alemania"
                    "Japon" -> tv1.text = "Bienvenido ${et1.text.toString()} " +  "${et2.text.toString()}\n" + " Nacionalidad: Japon"
                }
            }catch(e:Exception)
            {
                tv1.text ="Error al capturar los datos"
            }
        }
    }
    }
