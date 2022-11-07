package ru.com.simsgr.data.repositories

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.data.storage.interfaces.IMessagesStorage
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository

class MessagesRepository(val storage: IMessagesStorage):IMessagesRepository {
    override fun sendMessage(token: Token, message: Message, result: MutableLiveData<Message>) {
        storage.sendMessage(token = token, message = message)
    }

    override fun getUsersMessages(token: Token, to: String, limit: Int, page: Int,
                                  result: MutableLiveData<List<Message>>
    ) {
        storage.getUsersMessages(token = token, to=to, limit=limit, page = page)
    }


}