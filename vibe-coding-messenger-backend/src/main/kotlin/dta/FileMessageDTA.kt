package com.example.com.dta

import com.example.com.models.TextMessage
import kotlinx.serialization.Serializable

@Serializable
data class FileMessageDTA(val id: String, val user: String, val content: String, val timestamp: Long) {

    fun toTextMessage() =
        TextMessage(this.id, this.content, this.user, this.timestamp)

}

