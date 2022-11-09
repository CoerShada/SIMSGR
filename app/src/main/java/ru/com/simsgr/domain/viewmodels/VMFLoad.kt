package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCSyncAllMessages

class VMFLoad(usersRepository: IUserRepository, messageRepository: IMessagesRepository) : ViewModel() {

    private val uCSyncAllMessages : UCSyncAllMessages
    private val requestScope: CoroutineScope

    var complete = MutableLiveData<Boolean>()

    init{
        uCSyncAllMessages = UCSyncAllMessages(usersRepository, messageRepository)
        val viewModelJob = Job()
        requestScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    }


    fun syncronize(token: Token) = requestScope.launch {
        uCSyncAllMessages.execute(token)
        withContext(Dispatchers.Main){complete.value = true}
    }
}