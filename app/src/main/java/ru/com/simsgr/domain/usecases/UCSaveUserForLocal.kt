package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository

class UCSaveUserForLocal(private val repository: ISessionRepository) {

    suspend fun execute(user: CurrentUser): Boolean{
        return repository.saveUser(user = user)
    }
}