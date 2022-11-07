package ru.com.simsgr.data.di

import io.reactivex.Single
import retrofit2.http.*
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.OtherUser


interface API {

    @POST("/user/v1/login")
    @Headers("accept: application/json")
    fun login(@Body user: CurrentUser): Single<CurrentUser>

    @PUT("/user/v1/registration")
    fun register(@Body user: CurrentUser): Single<CurrentUser>

    @POST("/user/v1/update")
    fun update(@Query("user") user: CurrentUser): Single<CurrentUser> //Проверить

    @GET("/user/v1/users")
    @Headers("accept: application/json")
    fun getUsers(@Header("access_token") token: String,
                 @Query("included") included: String): Single<List<OtherUser>>

    @DELETE("/user/v1/users")
    fun logout(token: String): Single<Boolean> //Проверить

    @GET("/messenger/v1/send")
    @Headers("accept: application/json")
    fun sendMessage(@Header("access_token")token: String,
                    @Query("message")message: Message):Single<Message>

    @GET("/messenger/v1/messages")
    fun getMessages(@Header("access_token")token: String, @Query("from")to: String,
                    @Query("limit")limit: Int, @Query("page")page: Int):Single<List<Message>>

}