package ru.com.simsgr.data.storage.local

import android.content.Context
import ru.com.simsgr.data.room.MainDB
import ru.com.simsgr.domain.models.Message
class LocalMessageStorage(context: Context) {

    private val database: MainDB

    init {
        database = MainDB.getDB(context)
    }

    suspend fun sendMessage(message: Message){
        database.getDao().insertMessage(message)
    }

    fun getUsersMessages(
        from: String,
        limit: Int,
        page: Int
    ): List<Message> {
        return database.getDao().getMessagesByCurrentUser(id = from.toInt())
    }
}