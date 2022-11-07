package ru.com.simsgr.data.storage.remote

import io.reactivex.schedulers.Schedulers
import ru.com.simsgr.data.storage.interfaces.IMessagesStorage
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.Token

class RemoteMessageStorage: ARemoteStorage(), IMessagesStorage {

    override fun sendMessage(token: Token, message: Message): Message {
        return service.api.sendMessage(token = token.access, message = message).subscribeOn(Schedulers.io()).blockingGet()


    }

    override fun getUsersMessages(token: Token, from: String, limit: Int, page: Int): List<Message>
    {
        return service.api.getMessages(token = token.access, from = from, limit = limit, page = page).subscribeOn(Schedulers.io()).blockingGet()

    }
}