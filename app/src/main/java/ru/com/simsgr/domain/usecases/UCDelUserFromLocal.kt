package ru.com.simsgr.domain.usecases

import ru.com.simsgr.domain.repositories.ISessionRepository


class UCDelUserFromLocal(private val repository: ISessionRepository) {

    fun execute(){
        repository.delCurrentUser()
    }
}