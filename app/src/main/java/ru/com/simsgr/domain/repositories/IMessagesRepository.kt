package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token

interface IMessagesRepository {

    fun sendMessage(token: Token, message: Message): Message

    fun getUsersMessages(token: Token, to: String, limit: Int = Int.MAX_VALUE, page: Int = 0): List<Message>
}