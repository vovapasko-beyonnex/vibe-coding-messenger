package com.example.com.repositories

import com.example.com.models.BasicMessage

class InMemoryMessageMessageRepository: MessageRepository {
    override val messages: MutableList<BasicMessage> = mutableListOf();
}