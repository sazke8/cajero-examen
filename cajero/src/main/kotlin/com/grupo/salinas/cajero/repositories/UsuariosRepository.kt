package com.grupo.salinas.cajero.repositories;

import com.grupo.salinas.cajero.models.UsuariosModel
import org.springframework.data.jpa.repository.JpaRepository

interface UsuariosRepository: JpaRepository<UsuariosModel,Long>