package com.example.routes

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.bookRoutes() {
    routing {
        get("/books") {
            call.respondText("Listado libros")
        }
        post("/books") {
            call.respondText("Libro recibido")
        }
    }
}
