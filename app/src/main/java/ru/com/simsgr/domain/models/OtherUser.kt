package ru.com.simsgr.domain.models

import com.google.gson.annotations.SerializedName

data class OtherUser(
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String
)
