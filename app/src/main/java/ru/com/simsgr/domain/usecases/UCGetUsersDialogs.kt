package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetUsersDialogs(private val repository: IMessagesRepository) {

    suspend fun execute(users : List<User>): MutableList<Dialog> {

        val dialogs = mutableListOf<Dialog>()

        users.forEach{
            val messages = repository.getUsersMessagesFromLocal(from = it.id.toString(), limit=1).toMutableList()
            if (messages.isNotEmpty()) {
                dialogs.add(Dialog(it,messages))
            }
        }


        return dialogs
    }

}