package com.example.imagenbutonreto

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val imageButton: ImageButton by lazy {
        findViewById<ImageButton>(R.id.imageButton)
    }

    private val animales = listOf(
        R.mipmap.gato,
        R.mipmap.perro,
        R.mipmap.elefante
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton.setOnClickListener {
            val indice = animales.random()
            imageButton.setImageResource(indice)
        }
    }
}