package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.Message

interface IMessagesRepository {

    suspend fun sendMessage(message: Message): Message

    suspend fun getUsersMessagesFromRemote(from: String, limit: Int = Int.MAX_VALUE, page: Int = 0): List<Message>

    suspend fun getUsersMessagesFromLocal(from: String, limit: Int = Int.MAX_VALUE, page: Int = 0): List<Message>

}