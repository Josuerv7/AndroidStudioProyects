package com.example.examen_2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class ProductoEditar : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_editar)

        val producto = intent.getSerializableExtra("producto") as  Producto

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val n = findViewById<EditText>(R.id.edtNombre)
        val d = findViewById<EditText>(R.id.edtDescripcion)
        val c = findViewById<EditText>(R.id.edtCantidad)
        val cs = findViewById<EditText>(R.id.edtCosto)
        val v = findViewById<EditText>(R.id.edtVenta)
        val f = findViewById<EditText>(R.id.edtURL)
        val img = findViewById<ImageView>(R.id.imageView)
        val id = SpannableStringBuilder.valueOf(producto.id.toString())
        n.text = SpannableStringBuilder.valueOf(producto.nombre)
        d.text = SpannableStringBuilder.valueOf(producto.descripcion)
        c.text = SpannableStringBuilder.valueOf(producto.cantidad.toString())
        cs.text = SpannableStringBuilder.valueOf(producto.costo.toString())
        v.text = SpannableStringBuilder.valueOf(producto.venta.toString())
        f.text = SpannableStringBuilder.valueOf(producto.foto.toString())
        Picasso.get().load(producto.foto).into(img)

        f.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Cuando el texto en 'foto' cambia, carga la imagen con Picasso
                val url = s.toString()
                if (url.isNotEmpty()) {
                    Picasso.get().load(url).into(img)
                }
            }
        })

        btnGuardar.setOnClickListener{
            val admin = ProductosSQLite(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("Nombre",n.text.toString())
            registro.put("Descripcion",d.text.toString())
            registro.put("Cantidad",c.text.toString())
            registro.put("Costo",cs.text.toString())
            registro.put("Venta",v.text.toString())
            registro.put("Foto",f.text.toString())
            val cant = bd.update("Productos", registro, "Id=${id}", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe un producto con el id", Toast.LENGTH_SHORT).show()

            n.text.clear()
            d.text.clear()
            c.text.clear()
            cs.text.clear()
            v.text.clear()
            f.text.clear()
            img.setImageDrawable(null)

            this.finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        btnSalir.setOnClickListener {
            this.finish()
        }
    }
}