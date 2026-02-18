package com.example.com.models

class TextMessage(id: String, val content: String, val user: String, val timestamp: Long) : BasicMessage(id)