package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository


class UCSendMessage(private val repository: IMessagesRepository) {

    fun execute(token: Token, message: Message): Message{
        return repository.sendMessage(token = token, message = message)
    }
}