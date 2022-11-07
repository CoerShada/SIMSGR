package ru.com.simsgr.data.repositories

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.data.storage.IUserStorage
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

class UsersRepository(private val storage: IUserStorage): IUserRepository {

    override fun register(user: CurrentUser, result: MutableLiveData<CurrentUser>) {
        return storage.register(user = user, result=result)
    }

    override fun login(user: CurrentUser, result: MutableLiveData<CurrentUser>) {
        storage.login(user = user, result = result)
    }

    override fun update(user: CurrentUser, result: MutableLiveData<CurrentUser>) {
        return storage.update(user = user, result = result)
    }

    override fun getAllUsers(token: Token, included: String, result: MutableLiveData<List<OtherUser>>) {
        return storage.getUsers(token = token, included = included, result = result)
    }

    override fun logout(token: Token) {
        return storage.logout(token = token)
    }
}