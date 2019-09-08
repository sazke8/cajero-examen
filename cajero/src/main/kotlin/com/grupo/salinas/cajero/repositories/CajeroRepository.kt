package com.grupo.salinas.cajero.repositories;

import com.grupo.salinas.cajero.models.CajeroModel
import org.springframework.data.jpa.repository.JpaRepository

interface CajeroRepository: JpaRepository<CajeroModel,Int>

