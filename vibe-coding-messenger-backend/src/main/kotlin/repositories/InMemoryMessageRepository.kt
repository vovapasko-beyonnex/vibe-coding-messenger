package com.example.com.repositories

import com.example.com.models.BasicMessage

class InMemoryMessageRepository: MessageRepository {
    override val messages: MutableList<BasicMessage> = mutableListOf();
}