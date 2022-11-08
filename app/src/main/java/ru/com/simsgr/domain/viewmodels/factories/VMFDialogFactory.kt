package ru.com.simsgr.domain.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.data.repositories.MessagesRepository
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.domain.viewmodels.VMFDialog

class VMFDialogFactory(val user: User) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteMessageStorage = RemoteMessageStorage()

        return VMFDialog(
            user = user,
            messagesRepository = MessagesRepository(remoteMessageStorage),
        ) as T

    }
}