package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User

interface IUserRepository {

    suspend fun register(user: CurrentUser): CurrentUser

    suspend fun login(user: CurrentUser): CurrentUser

    suspend fun update(user: CurrentUser): CurrentUser

    suspend fun getAllUsers(token: Token, included: String): List<User>

    suspend fun logout(token: Token)

}