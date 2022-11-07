package ru.com.simsgr.domain.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.usecases.UCGetCurrentUser

class VMAMain(repository: SessionRepository): ViewModel() {

    private val ucGetCurrentUser = UCGetCurrentUser(repository)


    var user = MutableLiveData<CurrentUser>()

    fun getCurrentUser(){
        user.value = ucGetCurrentUser.execute()
    }

}