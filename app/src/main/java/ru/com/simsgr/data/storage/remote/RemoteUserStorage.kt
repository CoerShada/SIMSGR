package ru.com.simsgr.data.storage.remote

import io.reactivex.schedulers.Schedulers
import ru.com.simsgr.data.storage.interfaces.IUserStorage
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.User


class RemoteUserStorage: ARemoteStorage(), IUserStorage {


    override suspend fun register(user: CurrentUser): CurrentUser{
        return service.api.register(user = user).subscribeOn(Schedulers.io()).blockingGet()
    }

    override suspend fun login(user: CurrentUser): CurrentUser{
        return service.api.login(user = user).subscribeOn(Schedulers.io()).blockingGet()
    }

    override suspend fun update(user: CurrentUser): CurrentUser {
        return service.api.update(user = user).subscribeOn(Schedulers.io()).blockingGet()
    }

    override suspend fun getUsers(token: Token, included: String): List<User> {
        return service.api.getUsers(token = token.access, included = included).
                subscribeOn(Schedulers.io()).blockingGet()
    }

    override suspend fun logout(token: Token) {
        service.api.logout(token = token.access).subscribeOn(Schedulers.io()).blockingGet()
    }
}