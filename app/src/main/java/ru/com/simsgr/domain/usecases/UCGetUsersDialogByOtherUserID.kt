package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.*
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetUsersDialogByOtherUserID(private val repository: IMessagesRepository) {

    suspend fun execute(otherUser: User): List<Message> {

        return repository.getUsersMessagesFromLocal(
            from = otherUser.id.toString()
        )
    }
}