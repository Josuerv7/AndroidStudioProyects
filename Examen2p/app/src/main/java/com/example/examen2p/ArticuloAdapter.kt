package com.example.examen2p

import Articulo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ArticuloAdapter(private val articulos: List<Articulo>) : RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder>() {
    class ArticuloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
        val cantidadTextView: TextView = itemView.findViewById(R.id.cantidadTextView)
        val precioCostoTextView: TextView = itemView.findViewById(R.id.precioCostoTextView)
        val precioVentaTextView: TextView = itemView.findViewById(R.id.precioVentaTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_articulo, parent, false)
        return ArticuloViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = articulos[position]

        // Configurar las vistas en el elemento de la lista con los datos del artículo

        holder.nombreTextView.text = articulo.nombre
        holder.descripcionTextView.text = articulo.descripcion
        holder.cantidadTextView.text = "Cantidad: ${articulo.cantidad.toString()}"
        holder.precioCostoTextView.text = "Precio Costo: ${articulo.precioCosto.toString()}"
        holder.precioVentaTextView.text = "Precio Venta: ${articulo.precioVenta.toString()}"

        // Cargar una imagen (si estás usando una URL)
        Picasso.get()
            .load(articulo.imagen)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return articulos.size
    }
}

