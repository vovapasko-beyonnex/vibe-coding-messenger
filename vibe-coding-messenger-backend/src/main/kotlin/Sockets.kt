package com.example.com

import com.example.com.models.TextMessage
import com.example.com.repositories.InMemoryMessageMessageRepository
import com.example.com.repositories.MessageRepository
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import java.util.Collections
import kotlin.time.Duration.Companion.seconds


fun Application.configureSockets() {
    val repository = InMemoryMessageMessageRepository()

    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json{ ignoreUnknownKeys = true})
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        val sessions = Collections.synchronizedList(arrayListOf<WebSocketServerSession>())

        webSocket("/chat") { // websocketSession
            sessions.add(this)
            try {
                sendAllMessages(repository)
                while (true) {
                    val newTextMessage = receiveDeserialized<TextMessage>()
                    repository.addMessage(newTextMessage)
                    // Send the message to all open sessions
                    val it = sessions.iterator()
                    while (it.hasNext()) {
                        val session = it.next()
                        try {
                            session.sendSerialized(newTextMessage)
                        } catch (e: Exception) {
                            println(e.printStackTrace())
                            it.remove()
                        }
                    }
                }
            } catch (e: Exception) {
                // handle/log if needed
                println(e.printStackTrace())
            } finally {
                // Ensure removal of the session when done
                sessions.remove(this)
            }

        }
    }
}

private suspend fun DefaultWebSocketServerSession.sendAllMessages(repository: MessageRepository) {
    for (message in repository.getAllMessages()) {
        sendSerialized(message)
    }
}