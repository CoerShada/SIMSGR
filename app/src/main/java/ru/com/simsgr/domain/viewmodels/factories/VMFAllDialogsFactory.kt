package ru.com.simsgr.domain.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.data.repositories.MessagesRepository
import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.data.repositories.UsersRepository
import ru.com.simsgr.data.storage.local.LocalSessionStorage
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.data.storage.remote.RemoteUserStorage
import ru.com.simsgr.domain.viewmodels.VMALogin
import ru.com.simsgr.domain.viewmodels.VMFAllDialogs

class VMFAllDialogsFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteUserStorage = RemoteUserStorage()
        val remoteMessageStorage = RemoteMessageStorage()

        return VMFAllDialogs(MessagesRepository(remoteMessageStorage),UsersRepository(remoteUserStorage)) as T

    }

}