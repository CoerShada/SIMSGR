package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.OtherUser
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCGetAllUsers

class VMFUsers(usersRepository: IUserRepository): ViewModel() {

    private val ucGetUsers = UCGetAllUsers(repository = usersRepository)

    var users = MutableLiveData<List<OtherUser>>()

    fun getUsers(token: Token, included: String){
        users.value = ucGetUsers.execute(token = token, included = included)
    }
}