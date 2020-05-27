package com.example.minishop.Services

import com.example.minishop.Model.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    @GET("destination")
    fun getDestinationList(): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id")id: Int): Call<Destination>

    @POST("destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
        @Path("id")id: Int,
        @Field("city")city: String,
        @Field("description")desc: String,
        @Field("country")country: String
    ): Call<Destination>

    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id")id: Int): Call<Unit>
}