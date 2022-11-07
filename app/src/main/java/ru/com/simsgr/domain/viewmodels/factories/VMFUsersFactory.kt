package ru.com.simsgr.domain.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.data.repositories.UsersRepository
import ru.com.simsgr.data.storage.remote.RemoteUserStorage
import ru.com.simsgr.domain.viewmodels.VMFUsers

class VMFUsersFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val remoteUserStorage = RemoteUserStorage()

        return VMFUsers(UsersRepository(remoteUserStorage)) as T
    }
}