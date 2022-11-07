package ru.com.simsgr.data.storage.interfaces

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

interface IUserStorage{

    fun register(user: CurrentUser): CurrentUser

    fun login(user: CurrentUser): CurrentUser

    fun update(user: CurrentUser): CurrentUser

    fun getUsers(token: Token, included: String): List<OtherUser>

    fun logout(token: Token)

}