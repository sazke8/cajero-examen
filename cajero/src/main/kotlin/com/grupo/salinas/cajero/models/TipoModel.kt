package com.grupo.salinas.cajero.models

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "tipo", schema = "grupoSalinas", catalog = "")
class TipoModel {
    @get:Id
    @get:Column(name = "id")
    var id: Int = 0
    @get:Basic
    @get:Column(name = "nombre")
    var nombre: String? = null
    @get:Basic
    @get:Column(name = "descripcion")
    var descripcion: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val tipoModel = o as TipoModel?
        return id == tipoModel!!.id &&
                nombre == tipoModel.nombre &&
                descripcion == tipoModel.descripcion
    }

    override fun hashCode(): Int {
        return Objects.hash(id, nombre, descripcion)
    }
}
