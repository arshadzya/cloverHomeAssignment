package com.arshad.clovermobileapp.view.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arshad.clovermobileapp.data.api.ApiHelper
import com.arshad.clovermobileapp.view.dashboard.MainActivityRepository
import com.arshad.clovermobileapp.view.dashboard.MainActivityViewModel


/**
 * ViewModel provider factory to instantiate LocationDetailActivityViewModel.
 * Required given LocationDetailActivityViewModel has a non-empty constructor
 */

class LocationDetailActivityViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationDetailActivityViewModel::class.java)) {
            return LocationDetailActivityViewModel(LocationDetailActivityRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}