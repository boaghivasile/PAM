package com.example.minishop.Services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val URL = "http://10.0.2.2:9000/"

    //Create logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create OkHttp Client
    private val okHttp : OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

    //Create Retrofit Builder
    private val builder : Retrofit.Builder = Retrofit.Builder().baseUrl(URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .client(okHttp.build())

    //Create Retrofit Instance
    private val retrofit : Retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}