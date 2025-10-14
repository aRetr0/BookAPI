package com.example.routes

import com.example.models.Book
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing

val myBooks = mutableListOf<Book>()

fun Application.bookRoutes() {
    routing {
        get("/books") {
            if (myBooks.isNotEmpty()) call.respond(myBooks)
            else call.respondText("No hay libros en la lista")
        }
        post("/books") {
            try {
                myBooks.add(call.receive<Book>())
                call.respondText("Libro añadido")
            } catch (e: Exception) {
                call.respondText("No se ha podido añadir: ${e.message}")
            }
        }
        put("/books/{id}") {
            try {
                val id: String? = call.parameters["id"]
                // verifica si me ha dado un id
                if (id.isNullOrBlank()) throw Exception("No se ha dado id")

                // si ha dado id, busco el libro
                val index = myBooks.indexOfFirst { it.id == id }
                if (index != -1) {
                    myBooks[index] = call.receive<Book>()
                    call.respondText("Libro editado:\n ${myBooks[index]}")
                } else throw Exception("Id no existe") // si no encuentra el libro lo comunico

            } catch (e: Exception) {
                call.respondText("No se ha podido editar: ${e.message}")
            }
        }
        delete("/books/{id}") {
            try {
                val book: Book = checkId(call.parameters["id"])

                myBooks.remove(book)
                call.respondText("Libro eliminado")
            } catch (e: Exception) {
                call.respondText("No se ha podido eliminar: ${e.message}")
            }
        }
    }
}

fun checkId(id: String?): Book {
    if (id.isNullOrBlank()) throw Exception("No se ha dado un id")

    // si ha dado id, busco el libro
    val index = myBooks.indexOfFirst { it.id == id }
    if (index != -1) {
        return myBooks[index]
    } else throw Exception("Id no existe") // si no encuentra el libro lo comunico
}
