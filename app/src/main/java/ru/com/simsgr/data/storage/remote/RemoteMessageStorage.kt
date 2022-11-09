package ru.com.simsgr.data.storage.remote

import io.reactivex.schedulers.Schedulers
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Message

class RemoteMessageStorage(private val user: CurrentUser): ARemoteStorage() {

    suspend fun sendMessage(message: Message): Message {
        return service.api.sendMessage(token = user.token.access, message = message).subscribeOn(Schedulers.io()).blockingGet()
    }

    suspend fun getUsersMessages(from: String, limit: Int, page: Int): List<Message>
    {
        return service.api.getMessages(token = user.token.access, from = from, limit = limit, page = page).subscribeOn(Schedulers.io()).blockingGet()
    }

    suspend fun getNewMessages(): List<Message>{
        return service.api.getNewMessages(user.token.access).subscribeOn(Schedulers.io()).blockingGet()
    }
}