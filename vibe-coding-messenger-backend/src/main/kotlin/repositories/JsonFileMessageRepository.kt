package com.example.com.repositories

import com.example.com.dta.FileMessageDTA
import com.example.com.models.BasicMessage
import kotlinx.serialization.json.Json
import java.io.Closeable
import java.io.File

class JsonFileMessageRepository : MessageRepository, Closeable{
    private val pathname = "data.json"

    override val messages: MutableList<BasicMessage>
    override fun close() {
        File(pathname).writeText(Json.encodeToString(messages))
    }

    init {
        val fileContent = File(pathname).apply {
            if (!exists()) {
                writeText("[]")      // Create and initialize with an empty JSON array
            }
        }.readText()
        val messagesDta = Json.decodeFromString<MutableList<FileMessageDTA>>(fileContent).toMutableList()
        messages = messagesDta.map { it.toTextMessage() }.toMutableList()
    }

}