package com.arshad.clovermobileapp.view.dashboard

import com.arshad.clovermobileapp.data.api.ApiHelper

/**
 * Class that requests API and list of characters from the remote data source.
 */
class MainActivityRepository(private val apiHelper: ApiHelper)  {

    suspend fun getCharacters(count: Int) = apiHelper.getCharactersList(count)
}