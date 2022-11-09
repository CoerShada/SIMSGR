package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.com.simsgr.domain.models.*
import ru.com.simsgr.domain.repositories.IMessagesRepository
import ru.com.simsgr.domain.usecases.UCGetUsersDialogByOtherUserID
import ru.com.simsgr.domain.usecases.UCSendMessage
import java.lang.Thread.sleep

class VMFDialog(val user: User, messagesRepository: IMessagesRepository) : ViewModel() {

    val messages = MutableLiveData<List<Message>>()

    private val ucGetUsersMessagesByOtherUserID: UCGetUsersDialogByOtherUserID
    private val ucSendMessage: UCSendMessage
    private val requestScope: CoroutineScope

    init {
        ucGetUsersMessagesByOtherUserID = UCGetUsersDialogByOtherUserID(messagesRepository)
        ucSendMessage = UCSendMessage(messagesRepository)
        val viewModelJob = Job()
        requestScope = CoroutineScope(Dispatchers.IO + viewModelJob)
        getActualDialog()
        //Костыль, но работает
        Thread {
            while(true) {
                getActualDialog()
                sleep(500)
            }
        }.start()
    }



    private fun getActualDialog() = requestScope.launch{
        val messagesTemp = ucGetUsersMessagesByOtherUserID.execute(otherUser = user)
        withContext(Dispatchers.Main){messages.postValue(messagesTemp)}
        }

    fun sendMessage(message: Message) = requestScope.launch {
        val newMassage: Message = ucSendMessage.execute(message = message)
        withContext(Dispatchers.Main){ messages.value = listOf(newMassage) + messages.value!!}
    }


}