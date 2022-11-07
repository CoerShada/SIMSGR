package ru.com.simsgr.data.repositories

import ru.com.simsgr.data.storage.ISessionStorage
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository


class SessionRepository(private val storage: ISessionStorage): ISessionRepository {
    override fun getCurrentUser(): CurrentUser? {
        return storage.getCurrentUser()
    }

    override fun saveUser(user: CurrentUser): Boolean {
        return storage.saveUser(user)
    }

    override fun delCurrentUser() {
        storage.delCurrentUser()
    }
}