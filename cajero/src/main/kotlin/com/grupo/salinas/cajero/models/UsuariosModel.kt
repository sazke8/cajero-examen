package com.grupo.salinas.cajero.models

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "usuarios", schema = "grupoSalinas", catalog = "")
class UsuariosModel {
    @get:Id
    @get:Column(name = "id")
    var id: Int = 0
    @get:Basic
    @get:Column(name = "nombre")
    var nombre: String? = null
    @get:Basic
    @get:Column(name = "nip")
    var nip: Int = 0

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as UsuariosModel?
        return id == that!!.id &&
                nip == that.nip &&
                nombre == that.nombre
    }

    override fun hashCode(): Int {
        return Objects.hash(id, nombre, nip)
    }
}
