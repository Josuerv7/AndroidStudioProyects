package com.example.examen_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.examen_2.databinding.ItemProductoBinding
import com.squareup.picasso.Picasso

class ProductosAdapter(private val mContext: Context, private val listaProductos: List<Producto>) : ArrayAdapter<Producto>(mContext, 0, listaProductos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(mContext), parent, false)
        val productos = listaProductos[position]
        binding.id.text = "ID: ${productos.id}"
        binding.Nombre.text = productos.nombre
        binding.descripcion.text = productos.descripcion
        binding.Costo.text = "Precio Costo: $${productos.costo}"
        binding.Venta.text = "Precio Venta: $${productos.venta}"
        binding.Cantidad.text = "Cantidad: ${productos.cantidad}" // Eliminé el $ antes de productos.cantidad
        Picasso.get().load(productos.foto).into(binding.foto)

        return binding.root
    }
}
