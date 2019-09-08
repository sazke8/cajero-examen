package com.grupo.salinas.cajero.controllers;

import com.grupo.salinas.cajero.models.CajeroModel
import com.grupo.salinas.cajero.models.Cambio
import com.grupo.salinas.cajero.repositories.CajeroRepository
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
@RequestMapping("/v1/cajero")
class CajeroController {

    @Autowired
    lateinit var cajeroRepository: CajeroRepository

    @GetMapping
    fun getSaldoDisponible(): Double {
        var saldo = 0.0
        cajeroRepository.findAll().map {
            saldo += it.cantidad * it.denominacion
        }
        return saldo
    }

    @GetMapping("/retirar/{cantidad}")
    fun retirarSaldo(@PathVariable("cantidad") cantidad1: Double): List<Cambio> {
        var cantidad = cantidad1
        val cajero = cajeroRepository.findAll()
        val billetesDe1000 = Cambio(1000.0, 0)
        val billetesDe500 = Cambio(500.0, 0)
        val billetesDe200 = Cambio(200.0, 0)
        val billetesDe100 = Cambio(100.0, 0)
        val billetesDe50 = Cambio(50.0, 0)
        val billetesDe20 = Cambio(20.0, 0)
        val monedaDe10 = Cambio(10.0, 0)
        val monedaDe5 = Cambio(5.0, 0)
        val monedaDe2 = Cambio(2.0, 0)
        val monedaDe1 = Cambio(1.0, 0)
        val monedaDe50centavos = Cambio(0.5, 0)

        if (cantidad <= getSaldoDisponible() && cantidad > .50) {
            if (cantidad / cajero[0].denominacion > cajero[0].cantidad) {
                billetesDe1000.cantidad = cajero[0].cantidad
                cantidad -= cajero[0].denominacion * cajero[0].cantidad
                updateCantidadById(1, cajero[0].cantidad)
            } else if (cantidad > 0.0) {
                billetesDe1000.cantidad = (cantidad / cajero[0].denominacion).toInt()
                cantidad -= cajero[0].denominacion * billetesDe1000.cantidad
                updateCantidadById(1, billetesDe1000.cantidad)

            }
            if (cantidad / cajero[1].denominacion > cajero[1].cantidad) {
                billetesDe500.cantidad = cajero[1].cantidad
                cantidad -= cajero[1].denominacion * cajero[1].cantidad
                updateCantidadById(2, cajero[1].cantidad)
            } else if (cantidad > 0.0) {
                billetesDe500.cantidad = (cantidad / cajero[1].denominacion).toInt()
                cantidad -= cajero[1].denominacion * billetesDe500.cantidad
                updateCantidadById(2, billetesDe500.cantidad )

            }
            if (cantidad / cajero[2].denominacion > cajero[2].cantidad) {
                billetesDe200.cantidad = cajero[2].cantidad
                cantidad -= cajero[2].denominacion * cajero[2].cantidad
                updateCantidadById(3, cajero[2].cantidad)
            } else if (cantidad > 0.0) {
                billetesDe200.cantidad = (cantidad / cajero[2].denominacion).toInt()
                cantidad -= cajero[2].denominacion * billetesDe200.cantidad
                updateCantidadById(3, billetesDe200.cantidad)

            }
            if (cantidad / cajero[3].denominacion > cajero[3].cantidad) {
                billetesDe100.cantidad = cajero[3].cantidad
                cantidad -= cajero[3].denominacion * cajero[3].cantidad
                updateCantidadById(4, cajero[3].cantidad)

            } else if (cantidad > 0.0) {
                billetesDe100.cantidad = (cantidad / cajero[3].denominacion).toInt()
                cantidad -= cajero[3].denominacion * billetesDe100.cantidad
                updateCantidadById(4, billetesDe100.cantidad)

            }
            if (cantidad / cajero[4].denominacion > cajero[4].cantidad) {
                billetesDe50.cantidad = cajero[4].cantidad
                cantidad -= cajero[4].denominacion * cajero[4].cantidad
                updateCantidadById(5, cajero[4].cantidad)

            } else if (cantidad > 0.0) {
                billetesDe50.cantidad = (cantidad / cajero[4].denominacion).toInt()
                cantidad -= cajero[4].denominacion * billetesDe50.cantidad
                updateCantidadById(5, billetesDe50.cantidad)

            }
            if (cantidad / cajero[5].denominacion > cajero[5].cantidad) {
                billetesDe20.cantidad = cajero[5].cantidad
                cantidad -= cajero[5].denominacion * cajero[5].cantidad
                updateCantidadById(6, cajero[5].cantidad)

            } else if (cantidad > 0.0) {
                billetesDe20.cantidad = (cantidad / cajero[5].denominacion).toInt()
                cantidad -= cajero[5].denominacion * billetesDe20.cantidad
                updateCantidadById(6, billetesDe20.cantidad)

            }
            /*
            Para monedas
             */
            if (cantidad / cajero[6].denominacion > cajero[6].cantidad) {
                monedaDe10.cantidad = cajero[6].cantidad
                cantidad -= cajero[6].denominacion * cajero[6].cantidad
                updateCantidadById(7, cajero[6].cantidad)

            } else if (cantidad > 0.0) {
                monedaDe10.cantidad = (cantidad / cajero[6].denominacion).toInt()
                cantidad -= cajero[6].denominacion * monedaDe10.cantidad
                updateCantidadById(7, monedaDe10.cantidad)

            }
            /**
             * Para monedas de 5
             */
            if (cantidad / cajero[7].denominacion > cajero[7].cantidad) {
                monedaDe5.cantidad = cajero[7].cantidad
                cantidad -= cajero[7].denominacion * cajero[7].cantidad
                updateCantidadById(8, cajero[7].cantidad)

            } else if (cantidad > 0.0) {
                monedaDe5.cantidad = (cantidad / cajero[7].denominacion).toInt()
                cantidad -= cajero[7].denominacion * monedaDe5.cantidad
                updateCantidadById(8, monedaDe5.cantidad)

            }
            /**
             * Para monedas de 2
             */
            if (cantidad / cajero[8].denominacion > cajero[8].cantidad) {
                monedaDe2.cantidad = cajero[8].cantidad
                cantidad -= cajero[8].denominacion * cajero[8].cantidad
                updateCantidadById(9, cajero[8].cantidad)

            } else if (cantidad > 0.0) {
                monedaDe2.cantidad = (cantidad / cajero[8].denominacion).toInt()
                cantidad -= cajero[8].denominacion * monedaDe2.cantidad
                updateCantidadById(9, monedaDe2.cantidad)

            }
            /**
             * Para monedas de 1
             */
            if (cantidad / cajero[9].denominacion > cajero[9].cantidad) {
                monedaDe1.cantidad = cajero[9].cantidad
                cantidad -= cajero[9].denominacion * cajero[9].cantidad
                updateCantidadById(10, cajero[9].cantidad)

            } else if (cantidad > 0.0) {
                monedaDe1.cantidad = (cantidad / cajero[9].denominacion).toInt()
                cantidad -= cajero[9].denominacion * monedaDe1.cantidad
                updateCantidadById(10, monedaDe1.cantidad)

            }
            /**
             * Para monedas de 0.5
             */
            if (cantidad / cajero[10].denominacion > cajero[10].cantidad) {
                monedaDe50centavos.cantidad = cajero[10].cantidad
                cantidad -= cajero[10].denominacion * cajero[10].cantidad
                updateCantidadById(11, cajero[10].cantidad)

            } else if (cantidad > 0.0) {
                monedaDe50centavos.cantidad = (cantidad / cajero[10].denominacion).toInt()
                cantidad -= cajero[10].denominacion * monedaDe50centavos.cantidad
                updateCantidadById(11, monedaDe50centavos.cantidad)

            }
        }
        val cambio = listOf(billetesDe1000, billetesDe500, billetesDe200, billetesDe100, billetesDe50, billetesDe20,
                monedaDe10, monedaDe5, monedaDe2, monedaDe1, monedaDe50centavos)


        return cambio.filter { c -> c.cantidad > 0 }
    }

    @GetMapping("{id}")
    fun findById(@PathVariable("id") id: Int): ResponseEntity<Any> {
        val response = HashMap<String, Any>()
        val e: Optional<CajeroModel>
        try {
            e = cajeroRepository.findById(id)
        } catch (e: DataAccessException) {
            response["message"] = "Something go wrong consulting database"
            response["error"] = "${e.message} : ${e.mostSpecificCause.message}"
            println("Error ${e.mostSpecificCause.message}")
            return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        if (!e.isPresent) {
            response["message"] = "The id: $id not exist!"
            return ResponseEntity(response, HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(e.get(), HttpStatus.OK)
    }

    @PostMapping
    fun create(@Valid @RequestBody cajeroModel: CajeroModel): ResponseEntity<Any> {
        val response = HashMap<String, Any>()
        val e: CajeroModel
        try {
            e = cajeroRepository.save(cajeroModel)
        } catch (e: DataAccessException) {
            println("Error ${e.mostSpecificCause.message}")
            response["message"] = "Something go wrong inserting at database inCajero"
            response["error"] = "${e.message} : ${e.mostSpecificCause.message}"
            return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        response["message"] = "The Cajero has been inseted"
        response["entity"] = e
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PutMapping("{id}/{cantidad}")
    fun updateCantidadById(@PathVariable("id") id: Int, @PathVariable("cantidad") cantidad: Int) {
        val cajero = cajeroRepository.findById(id)
        if (cajero.isPresent) {
            cajero.get().cantidad = cajero.get().cantidad - cantidad
            cajeroRepository.save(cajero.get())
        }


    }
}

