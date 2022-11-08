package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.repositories.ISessionRepository

class UCGetCurrentUser(private val repository: ISessionRepository) {

    suspend fun execute(): CurrentUser?{
        return repository.getCurrentUser()
    }

}