package ru.com.simsgr.domain.models

data class Dialog(
    val otherUserId: OtherUser,
    var messages: List<Message>
)