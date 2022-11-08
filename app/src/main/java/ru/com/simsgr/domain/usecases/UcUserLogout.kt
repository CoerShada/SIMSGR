package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.ISessionRepository
import ru.com.simsgr.domain.repositories.IUserRepository


class UcUserLogout(private val usersRepository: IUserRepository,
                   private val sessionRepository: ISessionRepository)
{

    suspend fun execute(token: Token){
        usersRepository.logout(token = token)
        sessionRepository.delCurrentUser()
    }
}