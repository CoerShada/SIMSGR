package ru.com.simsgr.domain.usecases

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IMessagesRepository

class UCGetAllUsersDialogs(private val repository: IMessagesRepository) {

    fun execute(token: Token, user: CurrentUser, result: MutableLiveData<List<Dialog>>){
        //repository.getUsersMessages()
    }

}