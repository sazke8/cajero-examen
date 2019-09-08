package com.grupo.salinas.cajero.models

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "cajero", schema = "grupoSalinas", catalog = "")
class CajeroModel {
    @get:Id
    @get:Column(name = "id")
    var id: Int = 0
    @get:Basic
    @get:Column(name = "cantidad")
    var cantidad: Int = 0
    @get:Basic
    @get:Column(name = "denominacion")
    var denominacion: Double = 0.toDouble()

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as CajeroModel?
        return id == that!!.id &&
                cantidad == that.cantidad &&
                java.lang.Double.compare(that.denominacion, denominacion) == 0
    }

    override fun hashCode(): Int {
        return Objects.hash(id, cantidad, denominacion)
    }
}
