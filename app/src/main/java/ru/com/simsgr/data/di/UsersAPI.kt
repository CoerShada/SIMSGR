package ru.com.simsgr.data.di

import io.reactivex.Single
import retrofit2.http.*
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.OtherUser
import ru.com.simsgr.domain.models.Token


interface UsersAPI {

    @POST("/user/v1/login")
    @Headers("accept: application/json")
    fun login(@Body user: CurrentUser): Single<CurrentUser>

    @PUT("/user/v1/registration")
    fun register(@Body user: CurrentUser): Single<CurrentUser>

    @POST("/user/v1/update")
    fun update(@Query("user") user: String): Single<CurrentUser>

    @GET("/user/v1/users")
    @Headers("accept: application/json")
    fun getUsers(@Header("access_token") token: String, @Query("included") included: String): Single<List<OtherUser>>

    @DELETE("/user/v1/users")
    fun logout(token: String): Single<Boolean>


}