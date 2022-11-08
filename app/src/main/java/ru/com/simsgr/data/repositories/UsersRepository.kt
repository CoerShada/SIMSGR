package ru.com.simsgr.data.repositories

import ru.com.simsgr.data.storage.interfaces.IUserStorage
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User

class UsersRepository(private val storage: IUserStorage): IUserRepository {

    override suspend fun register(user: CurrentUser): CurrentUser {
        return storage.register(user = user)
    }

    override suspend fun login(user: CurrentUser): CurrentUser {
        return storage.login(user = user)
    }

    override suspend fun update(user: CurrentUser): CurrentUser {
        return storage.update(user = user)
    }

    override suspend fun getAllUsers(token: Token, included: String): List<User> {
        return storage.getUsers(token = token, included = included)
    }

    override suspend fun logout(token: Token) {
        storage.logout(token = token)
    }
}