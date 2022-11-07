package ru.com.simsgr.data.di

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Service private constructor() {
    private val API_URL : String = "http://212.75.210.227:8080"
    val api : API

    init {
        val retrofit = createRetrofit()
        api = retrofit.create(API::class.java)
    }

    companion object{
        val intent = Service()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(createOkHttpClient())
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    }


}