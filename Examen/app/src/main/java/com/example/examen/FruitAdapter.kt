package com.example.examen

import Fruit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.R // Asegúrate de que esta importación apunte al recurso R correcto

class FruitAdapter(private val fruits: List<Fruit>) :

RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {

        val view = LayoutInflater.from(parent.context)

            .inflate(R.layout.item_fruit, parent, false)

        return FruitViewHolder(view)

    }



    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.imageViewFruit.setImageResource(fruit.imageResId)
        holder.textViewName.text = fruit.name
        holder.textViewDescription.text = fruit.description
    }



    override fun getItemCount(): Int {

        return fruits.size

    }



    inner class FruitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewFruit: ImageView = itemView.findViewById(R.id.imageViewFruit)

        val textViewName: TextView = itemView.findViewById(R.id.textViewName)

        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

    }

}




