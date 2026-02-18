package com.example.com.dta

import com.example.com.models.TextMessage
import kotlinx.serialization.Serializable

@Serializable
data class MessageDTA(val user: String, val content: String, val timestamp: Long) {

    fun toTextMessage(id: String) =
        TextMessage(id, this.content, this.user, this.timestamp)

    companion object {
        fun fromTextMessage(message: TextMessage) =
            MessageDTA(message.user, message.content, message.timestamp)
    }

}

