package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.repositories.IMessagesRepository


class UCSendMessage(private val repository: IMessagesRepository) {

    suspend fun execute(message: Message): Message{
        return repository.sendMessage(message = message)
    }
}