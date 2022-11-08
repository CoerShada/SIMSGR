package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.*
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetUsersDialogByOtherUserID(private val repository: IMessagesRepository) {

    suspend fun execute(otherUser: User): MutableList<Message> {

        return repository.getUsersMessagesFromRemote(
            from = otherUser.id.toString()
        ).toMutableList()
    }
}