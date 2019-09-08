package com.grupo.salinas.cajero.controllers;

import com.grupo.salinas.cajero.models.UsuariosModel
import com.grupo.salinas.cajero.repositories.UsuariosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import kotlin.collections.HashMap

@CrossOrigin
@RestController
@RequestMapping("/v1/usuarios")
class UsuariosController {

    @Autowired
    lateinit var usuariosRepository: UsuariosRepository

    @GetMapping
    fun findAll(): MutableList<UsuariosModel> = usuariosRepository.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val response = HashMap<String, Any>()
        val e: Optional<UsuariosModel>
        try {
            e = usuariosRepository.findById(id)
        } catch (e: DataAccessException) {
            response["message"] = "Something go wrong consulting database"
            response["error"] = "${e.message} : ${e.mostSpecificCause.message}"
            println("Error ${e.mostSpecificCause.message}")
            return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        if (!e.isPresent) {
            response["message"] = "The id: ${id} not exist!"
            return ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(e.get(), HttpStatus.OK)
    }

    @PostMapping
    fun create(@Valid @RequestBody usuariosModel: UsuariosModel): ResponseEntity<Any> {
        val response = HashMap<String, Any>()
        val e: UsuariosModel
        try {
            e = usuariosRepository.save(usuariosModel)
        } catch (e: DataAccessException) {
            println("Error ${e.mostSpecificCause.message}")
            response["message"] = "Something go wrong inserting at database inUsuarios"
            response["error"] = "${e.message} : ${e.mostSpecificCause.message}"
            return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        response["message"] = "The Usuarios has been inseted"
        response["entity"] = e
        return ResponseEntity(response, HttpStatus.CREATED)
    }

}