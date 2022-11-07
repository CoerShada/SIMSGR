package ru.com.simsgr.data.storage.interfaces

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token

interface IMessagesStorage {

    fun sendMessage(token: Token, message: Message):Message

    fun getUsersMessages(token: Token, to: String, limit: Int, page: Int): List<Message>

}