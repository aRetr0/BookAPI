package com.example.routes

import com.example.models.Book
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
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
    }
}
