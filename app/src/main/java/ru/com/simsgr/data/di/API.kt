package ru.com.simsgr.data.di

import io.reactivex.Single
import retrofit2.http.*
import ru.com.simsgr.domain.models.Answer
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.User


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
                 @Query("included") included: String): Single<List<User>>

    @DELETE("/user/v1/logout")
    fun logout(@Header("access_token") token: String): Single<Answer>  //Костыль

    @POST("/messenger/v1/send")
    @Headers("accept: application/json")
    fun sendMessage(@Header("access_token")token: String,
                    @Body message: Message):Single<Message>

    @GET("/messenger/v1/messages")
    fun getMessages(@Header("access_token")token: String, @Query("from")from: String,
                    @Query("limit")limit: Int, @Query("page")page: Int):Single<List<Message>>

    @GET("/messenger/v1/new_messages")
    fun getNewMessages(@Header("access_token")token: String): Single<List<Message>>
}