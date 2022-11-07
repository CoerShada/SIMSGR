package ru.com.simsgr.domain.usecases

import androidx.lifecycle.MutableLiveData
import ru.com.simsgr.domain.models.AuthData
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.repositories.IUserRepository
import ru.com.simsgr.domain.models.CurrentUser

class UCRegisterUser(private val remoteRepository: IUserRepository) {

    fun execute(authData: AuthData, result: MutableLiveData<CurrentUser>) {
        val outgoingUser = CurrentUser(avatarUrl = "", id = 0, login = authData.login,
                                password = authData.password, token = Token("", "")
        )
        remoteRepository.register(user = outgoingUser, result = result)

    }
}