package ru.com.simsgr.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.com.simsgr.domain.models.Message

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Query("SELECT * FROM MESSAGES WHERE (`from` = :id1 AND `to` = :id2) OR (`from` = :id2 AND `to` = :id1) ORDER BY MESSAGES.date DESC")
    fun getMessagesByCurrentUser(id1: Int, id2: Int): List<Message>

    @Query("SELECT * FROM MESSAGES WHERE `from` = :id1 OR `to` = :id1 ORDER BY MESSAGES.date DESC")
    fun getMessagesByCurrentUser(id1: Int): List<Message>

    @Query("SELECT * FROM MESSAGES ORDER BY MESSAGES.date DESC")
    fun getNewMessages(): List<Message>

}