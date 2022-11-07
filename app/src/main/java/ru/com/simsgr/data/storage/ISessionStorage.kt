package ru.com.simsgr.data.storage

import ru.com.simsgr.domain.models.CurrentUser

interface ISessionStorage {

    fun getCurrentUser(): CurrentUser?

    fun saveUser(user: CurrentUser): Boolean

    fun delCurrentUser()

}