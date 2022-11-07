package ru.com.simsgr.data.storage

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

interface IUserStorage{

    fun register(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun login(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun update(user: CurrentUser, result: MutableLiveData<CurrentUser>)

    fun getUsers(token: Token, included: String, result: MutableLiveData<List<OtherUser>>)

    fun logout(token: Token)

}