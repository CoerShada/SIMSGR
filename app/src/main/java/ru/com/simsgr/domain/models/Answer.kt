package ru.com.simsgr.domain.models

import com.google.gson.annotations.SerializedName


data class Answer(
    @SerializedName("success")
    val success: Boolean
)
