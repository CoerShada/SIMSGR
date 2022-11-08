package ru.com.simsgr.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.com.simsgr.domain.models.Message

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMessage(message: Message)

    @Query("SELECT * FROM MESSAGES WHERE MESSAGES.`from` = :id OR MESSAGES.`to` = :id " +
            "ORDER BY MESSAGES.date DESC")
    fun getMessagesByCurrentUser(id: Int): List<Message>

    @Query("SELECT * FROM MESSAGES ORDER BY MESSAGES.date DESC")
    fun getNewMessages(): List<Message>

}