package com.example.examen2p

import Articulo
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val articulos = ArrayList<Articulo>()
    private lateinit var adapter: ArticuloAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        adapter = ArticuloAdapter(articulos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)
        val et4 = findViewById<EditText>(R.id.et4)
        val et5 = findViewById<EditText>(R.id.et5)
        val et6 = findViewById<EditText>(R.id.et6)
        val boton1 = findViewById<Button>(R.id.boton1)

        // Cargar datos de la base de datos al abrir la aplicación
        cargarDatosDesdeSQLite()

        boton1.setOnClickListener {
            val admin = AdminSQLLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", et1.text.toString())
            registro.put("descripcion", et2.text.toString())
            registro.put("cantidad", et3.text.toString())
            registro.put("preciocost", et4.text.toString())
            registro.put("preciovent", et5.text.toString())
            registro.put("imagen", et6.text.toString())
            val id = bd.insert("articulos", null, registro) // Obtén el ID de la inserción
            bd.close()

            if (id != -1L) {
                // Solo si la inserción se realizó con éxito, agrega el artículo a la lista con el ID asignado
                val nuevoArticulo = Articulo(
                    id.toInt(),
                    et1.text.toString(),
                    et2.text.toString(),
                    et3.text.toString().toInt(),
                    et4.text.toString().toDouble(),
                    et5.text.toString().toDouble(),
                    et6.text.toString()
                )
                articulos.add(nuevoArticulo)
                adapter.notifyDataSetChanged()
                et1.setText("")
                et2.setText("")
                et3.setText("")
                et4.setText("")
                et5.setText("")
                et6.setText("")
                Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Error al cargar los datos del artículo", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun eliminarArticulo(id: Int) {
        // Lógica para eliminar el artículo de la lista y de la base de datos
        val articuloAEliminar = articulos.find { it.id == id }
        if (articuloAEliminar != null) {
            // Elimina el artículo de la lista
            articulos.remove(articuloAEliminar)

            // Elimina el artículo de la base de datos
            val admin = AdminSQLLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            bd.delete("articulos", "id = ?", arrayOf(id.toString()))
            bd.close()

            // Notifica al adaptador que los datos han cambiado
            adapter.notifyDataSetChanged()
        }
    }


    private fun cargarDatosDesdeSQLite() {
        // Cargar datos de la base de datos y agregarlos a la lista de artículos
        val admin = AdminSQLLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.readableDatabase
        val cursor = bd.rawQuery("SELECT * FROM articulos", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
            val descripcion = cursor.getString(cursor.getColumnIndex("descripcion"))
            val cantidad = cursor.getInt(cursor.getColumnIndex("cantidad"))
            val preciocost = cursor.getDouble(cursor.getColumnIndex("preciocost"))
            val preciovent = cursor.getDouble(cursor.getColumnIndex("preciovent"))
            val imagen = cursor.getString(cursor.getColumnIndex("imagen"))


            val articulo = Articulo( id, nombre, descripcion, cantidad, preciocost, preciovent, imagen)
            articulos.add(articulo)
        }

        cursor.close()
        bd.close()
        adapter.notifyDataSetChanged()
    }
}

