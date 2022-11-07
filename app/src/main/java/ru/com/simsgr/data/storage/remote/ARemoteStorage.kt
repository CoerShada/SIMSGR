package ru.com.simsgr.data.storage.remote

import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import ru.com.simsgr.data.di.Service

abstract class ARemoteStorage {

    protected val LOG_TAG: String = javaClass.name
    protected val service = Service.intent
    protected val gson = Gson()


}