package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCSyncNewMessages(private val messageRepository: IMessagesRepository) {

    suspend fun execute() {
        val messages : List<Message> = messageRepository.getNewMessagesFromRemote()
        messages.forEach{
            messageRepository.saveMessageInLocal(it)
        }
    }
}