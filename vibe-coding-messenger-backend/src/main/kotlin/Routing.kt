package com.example.com


import com.example.com.models.MessageRepository
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources

import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        staticResources("/static", "static")
        get("/messages") {
            val messages = MessageRepository.getAllMessages()
            call.respond(messages)
        }
    }
}
