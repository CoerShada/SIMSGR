package ru.com.simsgr.domain.viewmodels.factories



import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.data.repositories.UsersRepository

import ru.com.simsgr.data.storage.LocalSessionStorage
import ru.com.simsgr.data.storage.RemoteUserStorage
import ru.com.simsgr.domain.viewmodels.VMALogin

class VMALoginFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteUserStorage = RemoteUserStorage()
        val localSessionStorage = LocalSessionStorage(context=context)

        return VMALogin(UsersRepository(remoteUserStorage), SessionRepository(localSessionStorage)) as T

    }
}