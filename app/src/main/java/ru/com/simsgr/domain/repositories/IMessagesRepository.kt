package ru.com.simsgr.domain.repositories

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token

interface IMessagesRepository {

    fun sendMessage(token: Token, message: Message, result: MutableLiveData<Message>)

    fun getUsersMessages(token: Token, to: String, limit: Int, page: Int, result: MutableLiveData<List<Message>>)
}