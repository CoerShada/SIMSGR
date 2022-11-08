package ru.com.simsgr.data.storage.remote

import ru.com.simsgr.data.di.Service
import ru.com.simsgr.domain.models.CurrentUser

abstract class ARemoteStorage() {

    protected val service = Service.intent


}