package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository
import ru.com.simsgr.domain.repositories.IUserRepository

class UCSyncAllMessages(private val userRepository: IUserRepository,
                        private val messagesRepository: IMessagesRepository)
{

    suspend fun execute(token: Token){
        val users = userRepository.getAllUsers(token)
        val messages = mutableListOf<Message>()
        users.forEach{
            messages.addAll(messagesRepository.getUsersMessagesFromRemote(it.id.toString()))
        }
        messages.forEach{
            messagesRepository.saveMessageInLocal(it)
        }
    }

}