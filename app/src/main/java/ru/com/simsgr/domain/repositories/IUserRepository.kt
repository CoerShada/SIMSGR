package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User

interface IUserRepository {

    fun register(user: CurrentUser): CurrentUser

    fun login(user: CurrentUser): CurrentUser

    fun update(user: CurrentUser): CurrentUser

    fun getAllUsers(token: Token, included: String): List<User>

    fun logout(token: Token)

}