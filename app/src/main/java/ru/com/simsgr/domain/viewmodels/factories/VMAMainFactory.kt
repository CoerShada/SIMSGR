package ru.com.simsgr.domain.viewmodels.factories


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.data.repositories.UsersRepository
import ru.com.simsgr.data.storage.local.LocalSessionStorage
import ru.com.simsgr.data.storage.remote.RemoteUserStorage

import ru.com.simsgr.domain.viewmodels.VMAMain

class VMAMainFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val localSessionStorage = LocalSessionStorage(context=context)
        val remoteUserStorage = RemoteUserStorage()
        return VMAMain(UsersRepository(remoteUserStorage), SessionRepository(localSessionStorage)) as T

    }
}