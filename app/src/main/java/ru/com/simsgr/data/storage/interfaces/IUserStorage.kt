package ru.com.simsgr.data.storage.interfaces

import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User

interface IUserStorage{

    suspend fun register(user: CurrentUser): CurrentUser

    suspend fun login(user: CurrentUser): CurrentUser

    suspend fun update(user: CurrentUser): CurrentUser

    suspend fun getUsers(token: Token, included: String): List<User>

    suspend fun logout(token: Token)

}