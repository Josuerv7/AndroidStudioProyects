package com.example.listreto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var listaTareas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la ListView
        listaTareas = findViewById(R.id.listaTareas)

        // Crear un ArrayList para almacenar las tareas
        val tareas = ArrayList<String>()

        // Crear un ArrayAdapter para la ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tareas)

        // Establecer el adaptador en la ListView
        listaTareas.adapter = adapter

        // Agregar un evento al botón
        val agregarButton = findViewById<Button>(R.id.agregarTarea)
        val tareaEditText = findViewById<EditText>(R.id.tarea)

        agregarButton.setOnClickListener {
            // Obtener la tarea ingresada por el usuario
            val nuevaTarea = tareaEditText.text.toString()

            if (nuevaTarea.isNotEmpty()) {
                // Agregar la tarea al ArrayList y notificar al adaptador
                tareas.add(nuevaTarea)
                adapter.notifyDataSetChanged()
                // Borrar el texto del EditText
                tareaEditText.text.clear()
            } else {
                Toast.makeText(this, "Ingrese una tarea válida", Toast.LENGTH_SHORT).show()
            }
        }

        // Agregar un evento a la ListView
        listaTareas.setOnItemClickListener { parent, view, position, id ->
            // Obtener la tarea seleccionada
            val tareaSeleccionada = adapter.getItem(position) as String
            // Mostrar un mensaje con la tarea seleccionada
            Toast.makeText(this, "Tarea seleccionada: $tareaSeleccionada", Toast.LENGTH_SHORT).show()
        }
    }
}
