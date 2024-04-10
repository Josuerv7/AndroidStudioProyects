package com.example.examen_2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent

import android.os.Bundle
import android.text.Editable

import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val agregar = findViewById<Button>(R.id.btnAgregar)
        val nombre = findViewById<EditText>(R.id.edtnombre)
        val descripcion = findViewById<EditText>(R.id.edtdescripcion)
        val cantidad = findViewById<EditText>(R.id.edtcantidad)
        val costo = findViewById<EditText>(R.id.edtcosto)
        val venta = findViewById<EditText>(R.id.edtventa)
        val foto = findViewById<EditText>(R.id.edturl)

        val ltv = findViewById<ListView>(R.id.ltv)
        val buscar = findViewById<Button>(R.id.btnBuscar)
        foto.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(sz: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {

            }
        })
        mostrar()
        agregar.setOnClickListener {
            try {
                val admin = ProductosSQLite(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val registro = ContentValues()
                registro.put("Nombre", nombre.text.toString())
                registro.put("Descripcion", descripcion.text.toString())
                registro.put("Cantidad", cantidad.text.toString().toInt())
                registro.put("Costo", costo.text.toString().toFloat())
                registro.put("Venta", venta.text.toString().toFloat())
                registro.put("Foto", foto.text.toString())
                bd.insert("Productos", null, registro)
                bd.close()
                Toast.makeText(this, "Articulo Registrado", Toast.LENGTH_SHORT).show()
                mostrar()
                nombre.text.clear()
                descripcion.text.clear()
                cantidad.text.clear()
                venta.text.clear()
                costo.text.clear()
                foto.text.clear()

            } catch (e: Exception) {

                Toast.makeText(this, "Error al registrar el artículo: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        buscar.setOnClickListener {
            busqueda()
        }
        ltv.onItemClickListener = this


    }

    @SuppressLint("Range", "MissingInflatedId")
    fun mostrar() {
        val ltv = findViewById<ListView>(R.id.ltv)
        val admin = ProductosSQLite(this, "administracion", null, 1)
        val edtb = findViewById<EditText>(R.id.edtb)
        val bd = admin.writableDatabase


        val fila = bd.rawQuery("select * from Productos", null)

        val productos = mutableListOf<Producto>()
        while (fila.moveToNext()) {
            val id = fila.getInt(fila.getColumnIndex("Id"))
            val nombre = fila.getString(fila.getColumnIndex("Nombre"))
            val descripcion = fila.getString(fila.getColumnIndex("Descripcion"))
            val cantidad = fila.getInt(fila.getColumnIndex("Cantidad"))
            val costo = fila.getFloat(fila.getColumnIndex("Costo"))
            val venta = fila.getFloat(fila.getColumnIndex("Venta"))
            val foto = fila.getString(fila.getColumnIndex("Foto"))
            productos.add(Producto(id, nombre, descripcion, cantidad, costo, venta, foto))
        }
        val adaptador = ProductosAdapter(this, productos)
        ltv.adapter = adaptador
        ltv.onItemClickListener = this
    }
    @SuppressLint("Range", "MissingInflatedId")
    fun busqueda() {
        val ltv = findViewById<ListView>(R.id.ltv)
        val admin = ProductosSQLite(this, "administracion", null, 1)
        val edtb = findViewById<EditText>(R.id.edtb)
        val bd = admin.writableDatabase


        val fila = bd.rawQuery("select * from Productos Where Id= ${edtb.text.toString()}", null)

        val productos = mutableListOf<Producto>()
        while (fila.moveToNext()) {
            val id = fila.getInt(fila.getColumnIndex("Id"))
            val nombre = fila.getString(fila.getColumnIndex("Nombre"))
            val descripcion = fila.getString(fila.getColumnIndex("Descripcion"))
            val cantidad = fila.getInt(fila.getColumnIndex("Cantidad"))
            val costo = fila.getFloat(fila.getColumnIndex("Costo"))
            val venta = fila.getFloat(fila.getColumnIndex("Venta"))
            val foto = fila.getString(fila.getColumnIndex("Foto"))
            productos.add(Producto(id, nombre, descripcion, cantidad, costo, venta, foto))
        }
        val adaptador = ProductosAdapter(this, productos)
        ltv.adapter = adaptador
        ltv.onItemClickListener = this
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val productos = parent?.adapter as ProductosAdapter
        val producto = productos.getItem(position)

        val options = arrayOf("Editar", "Eliminar")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Opciones")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {

                    val intent = Intent(this, ProductoEditar::class.java)
                    intent.putExtra("producto", producto)
                    startActivity(intent)
                }
                1 -> {

                    if (producto != null) {

                        val deleteDialog = AlertDialog.Builder(this)
                        deleteDialog.setTitle("Eliminar producto")
                        deleteDialog.setMessage("¿Deseas eliminar este producto?")
                        deleteDialog.setPositiveButton("Sí") { _, _ ->
                            eliminarProducto(producto)
                        }
                        deleteDialog.setNegativeButton("No") { _, _ ->

                        }
                        deleteDialog.show()
                    }
                }
            }
        }
        builder.show()
    }

    private fun eliminarProducto(producto: Producto) {
        val admin = ProductosSQLite(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val cant = bd.delete("Productos", "Id=${producto.id}", null)
        bd.close()
        if (cant == 1)
            Toast.makeText(this, "Se eliminó el articulo", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "No existe ese ID", Toast.LENGTH_SHORT).show()
        mostrar()
    }
}
