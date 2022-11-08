package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.CurrentUser

interface ISessionRepository {

    suspend fun getCurrentUser(): CurrentUser?

    suspend fun saveUser(user: CurrentUser): Boolean

    suspend fun delCurrentUser()

}