package com.example.lanzaractividadreto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)

        val edt1 = findViewById<EditText>(R.id.edt1)
        val edt2 = findViewById<EditText>(R.id.edt2)

        btn.setOnClickListener {
            val intento =Intent(this, SingUp::class.java)
            if(edt1.text.toString()=="josue" && edt2.text.toString()=="123")
                startActivity(intento)
            else
                Toast.makeText(this,"Credenciales invalidas, revisa porfavor",Toast.LENGTH_SHORT).show();
        }
    }
}