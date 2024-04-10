package com.example.toastreto

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy {
        findViewById<Button>(R.id.button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val snackbar = Snackbar.make(
                findViewById(android.R.id.content),
                "¡Puro udc!",
                Snackbar.LENGTH_SHORT
            )
            showToast()
            snackbar.show()
        }
    }
    private fun showToast() {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toast_root) as ViewGroup?)

        val toastText: TextView = layout.findViewById(R.id.toasttext)

        toastText.text = "!Puro udc¡"


        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout

        toast.show()
    }
}
