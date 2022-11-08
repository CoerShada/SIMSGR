package ru.com.simsgr.domain.viewmodels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.data.repositories.MessagesRepository
import ru.com.simsgr.data.storage.local.LocalMessageStorage
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.domain.viewmodels.VMFDialog

@Suppress("UNCHECKED_CAST")
class VMFDialogFactory(val currentUser: CurrentUser, val otherUser: User, val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val remoteMessageStorage = RemoteMessageStorage(currentUser)
        val localMessageStorage = LocalMessageStorage(context = context)


        return VMFDialog(
            user = otherUser,
            messagesRepository = MessagesRepository(remoteMessageStorage = remoteMessageStorage,
                                                    localMessageStorage = localMessageStorage)
        ) as T

    }
}