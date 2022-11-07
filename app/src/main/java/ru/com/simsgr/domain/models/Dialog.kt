package ru.com.simsgr.domain.models

data class Dialog(
    val otherUser: OtherUser,
    var messages: MutableList<Message>
)