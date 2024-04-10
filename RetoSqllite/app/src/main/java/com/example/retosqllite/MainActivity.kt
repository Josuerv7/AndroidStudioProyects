package com.example.retosqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val textViewResultado =findViewById<TextView>(R.id.textViewResultado)
        // Crear la base de datos
        database = openOrCreateDatabase("mi_base_de_datos", Context.MODE_PRIVATE, null)

        // Crear la tabla
        database.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY, nombre TEXT, correo TEXT)")

        // Agregar código para crear registros
        val botonCrear = findViewById<Button>(R.id.botonCrear)
        botonCrear.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
            val correo = findViewById<EditText>(R.id.editTextCorreo).text.toString()

            // Insertar el registro
            database.execSQL("INSERT INTO usuarios (nombre, correo) VALUES ('$nombre', '$correo')")
        }

        // Agregar código para leer registros
        val botonLeer = findViewById<Button>(R.id.botonLeer)
        botonLeer.setOnClickListener {
            // Consultar todos los registros
            val cursor = database.rawQuery("SELECT * FROM usuarios", null)

            // Crear una cadena para acumular la información
            val registros = StringBuilder()

            // Recorrer los registros
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val correo = cursor.getString(2)

                // Agregar la información al StringBuilder
                registros.append("ID: $id, Nombre: $nombre, Correo: $correo\n")
            }

                // Mostrar todos los registros en el textViewResultado
            textViewResultado.text = registros.toString()

            // Cerrar el cursor
            cursor.close()
        }

        // Agregar código para actualizar registros
        val botonActualizar = findViewById<Button>(R.id.botonActualizar)
        botonActualizar.setOnClickListener {
            val id = findViewById<EditText>(R.id.editTextId).text.toString().toInt()
            val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
            val correo = findViewById<EditText>(R.id.editTextCorreo).text.toString()

            // Actualizar el registro
            database.execSQL("UPDATE usuarios SET nombre='$nombre', correo='$correo' WHERE id=$id")
        }

        // Agregar código para eliminar registros
        val botonEliminar = findViewById<Button>(R.id.botonEliminar)
        botonEliminar.setOnClickListener {
            val id = findViewById<EditText>(R.id.editTextId).text.toString().toInt()

            // Eliminar el registro
            database.execSQL("DELETE FROM usuarios WHERE id=$id")
        }
    }
}