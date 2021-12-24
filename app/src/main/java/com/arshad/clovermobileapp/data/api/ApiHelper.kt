package com.arshad.clovermobileapp.data.api


class ApiHelper(private val apiService: ApiService) {

    //these two methods are helpers method for calling Retrofit api.
    //here I am using coroutine
    suspend fun getCharactersList(count: Int) = apiService.getCharactersList(count)
    suspend fun getLocationDetail(id: Int) = apiService.getLocationDetail(id)
  }