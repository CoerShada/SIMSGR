package ru.com.simsgr.domain.models

import com.google.gson.annotations.SerializedName

class CurrentUser(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    val token: Token
)


