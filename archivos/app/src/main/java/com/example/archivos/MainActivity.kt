package com.example.archivos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton1=findViewById<Button>(R.id.boton1)
        val et1=findViewById<EditText>(R.id.et1)
        if (fileList().contains("notas.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("notas.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                val todo = StringBuilder()
                while (linea != null) {
                    todo.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                et1.setText(todo)
            } catch (e: IOException) {
                Toast.makeText(this, "No escribio nada.", Toast.LENGTH_LONG).show()
            }
        }

        boton1.setOnClickListener {
            try {
                val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
                archivo.write(et1.text.toString())
                archivo.flush()
                archivo.close()

            } catch (e: IOException) {
            }
            Toast.makeText(this, "Los datos fueron grabados",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}