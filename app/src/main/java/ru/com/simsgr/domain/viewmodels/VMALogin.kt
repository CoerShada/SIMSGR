package ru.com.simsgr.domain.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.com.simsgr.domain.models.AuthData
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.*

class VMALogin(usersRepository: IUserRepository, sessionRepository: ISessionRepository): ViewModel() {
    private val uCLoginUser = UCLoginUser(usersRepository)
    private val uCRegisterUser = UCRegisterUser(usersRepository)
    private val uCSaveUserForLocal = UCSaveUserForLocal(sessionRepository)
    private val ucGetCurrentUser = UCGetCurrentUser(sessionRepository)


    var user = MutableLiveData<CurrentUser>()

    fun login(authData: AuthData) = viewModelScope.launch{
        user.value = uCLoginUser.execute(authData)
    }

    fun register(authData: AuthData) = viewModelScope.launch {
        user.value = uCRegisterUser.execute(authData)

    }

    fun saveCurrentUser() = viewModelScope.launch{
        uCSaveUserForLocal.execute(user = user.value!!)
    }

    fun authAuto() = viewModelScope.launch{
        val tempUser = ucGetCurrentUser.execute()
        if (tempUser!=null){
            login(authData = AuthData(tempUser.login, tempUser.password))
        }
    }


}