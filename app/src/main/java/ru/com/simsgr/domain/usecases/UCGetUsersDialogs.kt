package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.domain.models.OtherUser
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetUsersDialogs(private val repository: IMessagesRepository) {

    fun execute(user: CurrentUser, users : List<OtherUser>): MutableList<Dialog> {

        val dialogs = mutableListOf<Dialog>()


        users.forEach{
            val messages = repository.getUsersMessages(token = user.token,
                to = it.id.toString()).toMutableList()
            if (messages.isNotEmpty()) {
                dialogs.add(Dialog(it,messages))
            }
        }


        return dialogs
    }

}