package ru.com.simsgr.domain.models

import com.google.gson.annotations.SerializedName

class CurrentUser(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    id: Int,
    login: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("token")
    val token: Token
): User(id = id, login = login)


