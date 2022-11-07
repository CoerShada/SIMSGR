package ru.com.simsgr.data.storage.interfaces

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token

interface IMessagesStorage {

    fun sendMessage(token: Token, message: Message):Message

    fun getUsersMessages(token: Token, from: String, limit: Int, page: Int): List<Message>

}