package com.example.examen
import Fruit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {



    private lateinit var recyclerView: RecyclerView

    private lateinit var fruitAdapter: FruitAdapter



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        val fruits = generateFruits()

        fruitAdapter = FruitAdapter(fruits)



        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = fruitAdapter

    }



    private fun generateFruits(): List<Fruit> {

        // Simulated data

        val apple = Fruit("El Santo", "Rodolfo Guzmán Huerta, conocido como El Santo, fue un luchador profesional y actor mexicano. También apodado como El enmascarado de plata, es uno de los luchadores más famosos de México y el mundo.", R.drawable.elsanto)

        val banana = Fruit("Mistico", "Protagonista de uno de los más grandes fenómenos de la historia de este deporte y el motor del nuevo boom este deporte.", R.drawable.mistico)

        val orange = Fruit("Soberano JR", "Luchador muy espectacular con talento y pasión por la lucha libre, ya que su abuelo, padre y tío le heredaron este gusto.", R.drawable.soberano)

        val tinieblas = Fruit("Tinieblas", "Manuel Leal Peña, más conocido por su nombre en el ring Tinieblas, es un luchador profesional mexicano semi-retirado, que aparecía junto a su mascota \"Alushe\", que era un duende maya.", R.drawable.tinieblas)

        val nacho = Fruit("Nacho Libre", "Sergio Gutiérrez Benítez es el nombre civil de Fray Tormenta (el verdadero Nacho Libre), un sacerdote y luchador mexicano con una gran singularidad: su amor por los huérfanos. Nacido en 1944, a la edad de 22 años ingresó a la Orden religiosa de los Escolapios.", R.drawable.nacho)



        return listOf(apple, banana, orange, tinieblas, nacho)

    }

}
