package ru.com.simsgr.domain.repositories

import ru.com.simsgr.domain.models.CurrentUser

interface ISessionRepository {

    fun getCurrentUser(): CurrentUser?

    fun saveUser(user: CurrentUser): Boolean

    fun delCurrentUser()

}