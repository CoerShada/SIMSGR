package ru.com.simsgr.data.repositories

import ru.com.simsgr.data.storage.interfaces.IMessagesStorage
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository

class MessagesRepository(val storage: IMessagesStorage):IMessagesRepository {
    override fun sendMessage(token: Token, message: Message): Message {
        return storage.sendMessage(token = token, message = message)
    }

    override fun getUsersMessages(token: Token, from: String, limit: Int, page: Int): List<Message> {
        return storage.getUsersMessages(token = token, from=from, limit=limit, page = page)
    }


}