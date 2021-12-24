package com.arshad.clovermobileapp.view.location

import com.arshad.clovermobileapp.data.api.ApiHelper


/**
 * Class that requests API and list of characters from the remote data source.
 */
class LocationDetailActivityRepository (private val apiHelper: ApiHelper)  {

    suspend fun getLocationDetail(id: Int) = apiHelper.getLocationDetail(id)
}