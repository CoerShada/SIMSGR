package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCGetAllUsers

class VMFUsers(usersRepository: IUserRepository): ViewModel() {

    private val ucGetUsers = UCGetAllUsers(repository = usersRepository)

    var users = MutableLiveData<List<User>>()

    fun getUsers(token: Token, included: String) = viewModelScope.launch{
        users.value = ucGetUsers.execute(token = token, included = included)
    }
}