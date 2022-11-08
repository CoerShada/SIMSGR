package ru.com.simsgr.data.storage.interfaces

import ru.com.simsgr.domain.models.CurrentUser

interface ISessionStorage {

    suspend fun getCurrentUser(): CurrentUser?

    suspend fun saveUser(user: CurrentUser): Boolean

    suspend fun delCurrentUser()

}