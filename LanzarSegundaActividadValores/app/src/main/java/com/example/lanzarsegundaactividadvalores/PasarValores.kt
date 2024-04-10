package com.example.lanzarsegundaactividadvalores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.content.Intent


class PasarValores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasar_valores)

        val bundle = intent.extras
        val dato = bundle?.getString("direccion")
        val webView1=findViewById<WebView>(R.id.wbv)
        val boton1=findViewById<Button>(R.id.btn2)
        webView1.loadUrl("https://${dato}")
        boton1.setOnClickListener {
            finish();
        }
    }
}