package ru.com.simsgr.domain.repositories

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

interface IUserRepository {

    fun register(user: CurrentUser): CurrentUser

    fun login(user: CurrentUser): CurrentUser

    fun update(user: CurrentUser): CurrentUser

    fun getAllUsers(token: Token, included: String): List<OtherUser>

    fun logout(token: Token)

}