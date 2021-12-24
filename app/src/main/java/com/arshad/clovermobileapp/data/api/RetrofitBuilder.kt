package com.arshad.clovermobileapp.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    //this is base URL it will be provided by secure or encrypted but for the time being will not encrypt.
    private const val BASE_URL = "https://rickandmortyapi.com/api/"


    //Below line of code is used for getting out-put in json format.
    var gson = GsonBuilder()
        .setLenient()
        .create()


    //These line of code is builder code for retrofit.
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}