package com.example.examen_2

import java.io.Serializable

class Producto(
    val id:Int, val nombre:String, val descripcion:String, val cantidad:Int, val costo: Float, val venta: Float,
    val foto:String) : Serializable