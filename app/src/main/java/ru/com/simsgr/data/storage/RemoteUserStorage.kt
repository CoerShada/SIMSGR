package ru.com.simsgr.data.storage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.com.simsgr.data.di.UsersService
import ru.com.simsgr.domain.models.Token
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser


class RemoteUserStorage: IUserStorage {

    private val LOG_TAG: String = javaClass.name


    private val usersService = UsersService()
    private val disposable: CompositeDisposable = CompositeDisposable()



    private val gson = Gson()


    override fun register(user: CurrentUser, result: MutableLiveData<CurrentUser>) {

        disposable.add(
            usersService.api.register(user = user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.value = it
                Log.d(LOG_TAG, "Register has been successful")
            }, {
                Log.d(LOG_TAG, "Error register")

            })
        )

    }

    override fun login(user: CurrentUser, result: MutableLiveData<CurrentUser>){
        disposable.add(
            usersService.api.login(user = user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result.value = it
                    Log.d(LOG_TAG, "Auth has been successful")

                }, {
                    Log.d(LOG_TAG, "Error auth")

                })
        )

    }

    override fun update(user: CurrentUser, result: MutableLiveData<CurrentUser>) {
        disposable.add(
            usersService.api.update(user = gson.toJson(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result.value = it
                    Log.d(LOG_TAG, "Update user has been successful")
                }, {
                    Log.d(LOG_TAG, "Error update user")

                })
        )

    }

    override fun getUsers(token: Token, included: String, result: MutableLiveData<List<OtherUser>>) {

        disposable.add(
            usersService.api.getUsers(token = token.access, included = included)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    result.value = it
                    Log.d(LOG_TAG, "List user has been gated ${it.size}")

                }, {
                    Log.d(LOG_TAG, "Error getting list of users ")

                })
        )

    }

    override fun logout(token: Token) {
        disposable.add(
            usersService.api.logout(token = gson.toJson(token))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        Log.d(LOG_TAG, "User logged out")

    }
}