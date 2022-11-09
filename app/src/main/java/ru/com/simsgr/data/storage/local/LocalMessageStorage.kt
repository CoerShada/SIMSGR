package ru.com.simsgr.data.storage.local

import android.content.Context

import ru.com.simsgr.data.room.MainDB
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Message

class LocalMessageStorage(context: Context, private val user: CurrentUser) {

    private val database: MainDB

    init {
        database = MainDB.getDB(context)
    }

    fun saveMessage(message: Message){
        database.getDao().insertMessage(message)
    }

    fun getUsersMessages(
        from: String,
        limit: Int,
        page: Int
    ): List<Message> {

        return if (from.isNotEmpty())
            database.getDao().getMessagesByCurrentUser(id1 = from.toInt(), user.id)
        else
            database.getDao().getMessagesByCurrentUser(user.id)
    }
}