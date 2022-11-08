package ru.com.simsgr.domain.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCGetCurrentUser
import ru.com.simsgr.domain.usecases.UcUserLogout

class VMAMain(usersRepository: IUserRepository, sessionRepository: SessionRepository): ViewModel() {

    private val ucGetCurrentUser = UCGetCurrentUser(sessionRepository)
    private val ucUserLogout = UcUserLogout(usersRepository, sessionRepository)



    var user = MutableLiveData<CurrentUser>()

    fun getCurrentUser() = viewModelScope.launch{
        user.value = ucGetCurrentUser.execute()
    }

    fun logout()  = viewModelScope.launch {
        ucUserLogout.execute(token = user.value!!.token)
    }

}