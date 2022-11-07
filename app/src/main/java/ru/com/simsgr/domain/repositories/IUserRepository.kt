package ru.com.simsgr.domain.repositories

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

interface IUserRepository {

    fun register(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun login(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun update(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun getAllUsers(token: Token, included: String, result: MutableLiveData<List<OtherUser>>)

    fun logout(token: Token)

}