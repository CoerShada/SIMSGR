package ru.com.simsgr.data.repositories

import ru.com.simsgr.data.storage.local.LocalMessageStorage
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.repositories.IMessagesRepository

class MessagesRepository(
    private val remoteMessageStorage: RemoteMessageStorage,
    private val localMessageStorage: LocalMessageStorage,
    ):IMessagesRepository
{
    override suspend fun sendMessage(message: Message): Message {
        val editedMessage : Message = remoteMessageStorage.sendMessage(message = message)
        localMessageStorage.sendMessage(message = editedMessage)
        return editedMessage
    }

    override suspend fun getUsersMessagesFromRemote(from: String, limit: Int, page: Int): List<Message> {
        return remoteMessageStorage.getUsersMessages(from=from, limit=limit, page = page)
    }

    override suspend fun getUsersMessagesFromLocal(from: String, limit: Int, page: Int): List<Message> {
        return localMessageStorage.getUsersMessages(from = from, limit = limit, page = page)
    }


}