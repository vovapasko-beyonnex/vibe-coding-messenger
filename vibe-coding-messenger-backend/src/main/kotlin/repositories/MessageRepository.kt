package com.example.com.repositories

import com.example.com.models.BasicMessage

interface MessageRepository {
    val messages: MutableList<BasicMessage>

    fun addMessage(message: BasicMessage) {
        messages.add(message)
    }

    fun getAllMessages(): MutableList<BasicMessage> {
        return messages
    }

    fun findMessageById(id: String): BasicMessage? {
        return messages.find { it.id == id }
    }

    fun removeMessage(id: String): Boolean {
        return messages.removeIf { it.id == id }
    }

}