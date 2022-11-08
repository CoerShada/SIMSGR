package ru.com.simsgr.domain.models

data class Dialog(
    val otherUser: User,
    var messages: MutableList<Message>
)