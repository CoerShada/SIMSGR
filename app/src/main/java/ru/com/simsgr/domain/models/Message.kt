package ru.com.simsgr.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGES")
data class Message(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val message: String,
    val date: Long,
    var delivered: Boolean,
    val from: Int,
    val to: Int

)
