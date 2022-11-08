package ru.com.simsgr.domain.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.data.repositories.MessagesRepository
import ru.com.simsgr.data.repositories.UsersRepository
import ru.com.simsgr.data.storage.local.LocalMessageStorage
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.data.storage.remote.RemoteUserStorage
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.viewmodels.VMFAllDialogs

@Suppress("UNCHECKED_CAST")
class VMFAllDialogsFactory(private val user: CurrentUser, private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteUserStorage = RemoteUserStorage()
        val remoteMessageStorage = RemoteMessageStorage(user = user)
        val localMessageStorage = LocalMessageStorage(context = context)

        return VMFAllDialogs(MessagesRepository(remoteMessageStorage, localMessageStorage),
            UsersRepository(remoteUserStorage)) as T

    }

}