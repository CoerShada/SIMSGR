package ru.com.simsgr.domain.usecases


import ru.com.simsgr.domain.models.OtherUser
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IUserRepository


class UCGetAllUsers(private val repository: IUserRepository) {

    fun execute(token : Token, included: String): List<OtherUser>{
        return repository.getAllUsers(token = token, included = included)
    }
}