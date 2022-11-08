package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.com.simsgr.domain.models.*
import ru.com.simsgr.domain.repositories.IMessagesRepository
import ru.com.simsgr.domain.usecases.UCGetUsersDialogByOtherUserID
import ru.com.simsgr.domain.usecases.UCSendMessage

class VMFDialog(val user: User, messagesRepository: IMessagesRepository) : ViewModel() {


    val messages = MutableLiveData<List<Message>>()


    private val ucGetUsersMessagesByOtherUserID: UCGetUsersDialogByOtherUserID
    private val ucSendMessage: UCSendMessage

    init {
        ucGetUsersMessagesByOtherUserID = UCGetUsersDialogByOtherUserID(messagesRepository)
        ucSendMessage = UCSendMessage(messagesRepository)

        getActualDialog()
    }

    fun getActualDialog() = viewModelScope.launch{
        messages.value = ucGetUsersMessagesByOtherUserID.execute(otherUser = user)
    }

    fun sendMessage(message: Message) = viewModelScope.launch {
        val newMassage: Message = ucSendMessage.execute(message = message)
        messages.value = listOf(newMassage) + messages.value!!
    }
}