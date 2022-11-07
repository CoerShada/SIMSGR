package ru.com.simsgr.domain.usecases

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser

class UCGetAllUsers(private val repository: IUserRepository) {

    fun execute(token : Token, included: String, result : MutableLiveData<List<OtherUser>>){
        repository.getAllUsers(token = token, included = included, result= result)
    }
}