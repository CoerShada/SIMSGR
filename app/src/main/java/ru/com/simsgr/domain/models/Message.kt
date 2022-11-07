package ru.com.simsgr.domain.models

import java.util.Date

data class Message(
    val id: Long,
    val message: String,
    val date: Long,
    var delivered: Boolean,
    val from: Int,
    val to: Int

)
