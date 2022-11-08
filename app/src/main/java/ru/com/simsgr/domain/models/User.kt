package ru.com.simsgr.domain.models

import com.google.gson.annotations.SerializedName

open class User(
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String
)
