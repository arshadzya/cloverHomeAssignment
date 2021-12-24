package com.arshad.clovermobileapp.data.api

import com.arshad.clovermobileapp.data.model.Characters
import com.arshad.clovermobileapp.data.model.LocationDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("character")
    suspend fun getCharactersList(@Query("count") count: Int): Characters

    @GET("location/{locationId}")
    suspend fun getLocationDetail(@Path("locationId") id: Int): LocationDetail

}