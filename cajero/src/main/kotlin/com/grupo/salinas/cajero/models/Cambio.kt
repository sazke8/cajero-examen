package com.grupo.salinas.cajero.models

class Cambio(
    var denominacion:Double,
    var cantidad:Int
){
    override fun toString(): String {
        return "Cambio(denominacion=$denominacion, cantidad=$cantidad)"
    }
}

