package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.domain.repositories.IMessagesRepository
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.usecases.UCGetAllUsers
import ru.com.simsgr.domain.usecases.UCGetUsersDialogs

class VMFAllDialogs(messagesRepository : IMessagesRepository,
                    userRepository: IUserRepository): ViewModel() {

    private val uCGetUserDialogs : UCGetUsersDialogs
    private val uCGetAllUsers: UCGetAllUsers



    init{
        uCGetUserDialogs = UCGetUsersDialogs(messagesRepository)
        uCGetAllUsers = UCGetAllUsers(userRepository)
    }

    var dialogs = MutableLiveData<List<Dialog>>()

    fun getUsersDialogs(user: CurrentUser){
        val users = uCGetAllUsers.execute(user.token, "")
        dialogs.value = uCGetUserDialogs.execute(user = user, users = users)
    }



}