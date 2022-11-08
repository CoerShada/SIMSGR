package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.*
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetUsersDialogByOtherUserID(private val repository: IMessagesRepository) {

    fun execute(token: Token, otherUser: User): MutableList<Message> {

        val messages = repository.getUsersMessages(
            token = token,
            to = otherUser.id.toString()
        ).toMutableList()

        return messages
    }
}