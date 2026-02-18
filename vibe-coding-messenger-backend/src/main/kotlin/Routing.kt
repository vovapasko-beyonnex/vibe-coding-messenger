package com.example.com


import com.example.com.repositories.InMemoryMessageRepository
import com.example.com.repositories.JsonFileMessageRepository
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources

import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    val repository = JsonFileMessageRepository()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        staticResources("/static", "static")
        get("/messages") {
            val messages = repository.getAllMessages()
            call.respond(messages)
        }
    }
}
