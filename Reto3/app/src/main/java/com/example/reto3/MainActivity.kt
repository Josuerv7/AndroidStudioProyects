package com.example.reto3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var checkBoxRead: CheckBox
    private lateinit var checkBoxWrite: CheckBox
    private lateinit var buttonSubmit: Button
    private lateinit var textViewWelcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencias a las vistas
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        checkBoxRead = findViewById(R.id.checkBoxRead)
        checkBoxWrite = findViewById(R.id.checkBoxWrite)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textViewWelcomeMessage = findViewById(R.id.textViewWelcomeMessage)

        // Configurar un OnClickListener para el botón
        buttonSubmit.setOnClickListener(View.OnClickListener {
            // Obtener el texto ingresado en los EditText
            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()

            // Verificar las preferencias del usuario
            var preferences = ""
            if (checkBoxRead.isChecked) {
                preferences += "Leer, "
            }
            if (checkBoxWrite.isChecked) {
                preferences += "Escribir, "
            }

            // Construir el mensaje de bienvenida
            var welcomeMessage = "¡Bienvenido, $firstName $lastName!"
            if (preferences.isNotEmpty()) {
                welcomeMessage += " Tus preferencias son: ${preferences.substring(0, preferences.length - 2)}."
            }

            // Mostrar el mensaje de bienvenida en el TextView
            textViewWelcomeMessage.text = welcomeMessage
        })
    }
}
