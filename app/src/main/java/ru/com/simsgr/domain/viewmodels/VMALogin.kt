package ru.com.simsgr.domain.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.com.simsgr.domain.models.AuthData
import ru.com.simessenger.domain.usecases.UCLoginUser
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCGetCurrentUser
import ru.com.simsgr.domain.usecases.UCRegisterUser
import ru.com.simsgr.domain.usecases.UCSaveUserForLocal

class VMALogin(usersRepository: IUserRepository, sessionRepository: ISessionRepository): ViewModel() {


    private val uCLoginUser = UCLoginUser(usersRepository)
    private val uCRegisterUser = UCRegisterUser(usersRepository)
    private val uCSaveUserForLocal = UCSaveUserForLocal(sessionRepository)
    private val ucGetCurrentUser = UCGetCurrentUser(sessionRepository)


    var user = MutableLiveData<CurrentUser>()

    fun login(authData: AuthData){
        uCLoginUser.execute(authData, user)
    }

    fun register(authData: AuthData) {
        uCRegisterUser.execute(authData, user)
    }

    fun saveCurrentUser() {
        uCSaveUserForLocal.execute(user = user.value!!)
    }

    fun authAuto(){
        var tempUser = ucGetCurrentUser.execute()
        if (tempUser!=null){
            login(authData = AuthData(tempUser.login, tempUser.password))
        }
    }


}