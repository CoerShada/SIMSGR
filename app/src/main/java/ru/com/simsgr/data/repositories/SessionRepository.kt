package ru.com.simsgr.data.repositories

import ru.com.simsgr.data.storage.interfaces.ISessionStorage
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository


class SessionRepository(private val storage: ISessionStorage): ISessionRepository {
    override suspend fun getCurrentUser(): CurrentUser? {
        return storage.getCurrentUser()
    }

    override suspend fun saveUser(user: CurrentUser): Boolean {
        return storage.saveUser(user)
    }

    override suspend fun delCurrentUser() {
        storage.delCurrentUser()
    }
}