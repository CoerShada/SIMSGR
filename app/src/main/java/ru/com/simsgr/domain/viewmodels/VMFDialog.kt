package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    }

    fun getActualDialog(token: Token){
        messages.value = ucGetUsersMessagesByOtherUserID.execute(token = token, otherUser = user)
    }

    fun sendMessage(token: Token, message: Message){
        val newMassage: Message = ucSendMessage.execute(token = token, message = message)
        messages.value = listOf(newMassage) + messages.value!!
    }
}