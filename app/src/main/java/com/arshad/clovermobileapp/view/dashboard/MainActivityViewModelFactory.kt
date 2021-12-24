package com.arshad.clovermobileapp.view.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arshad.clovermobileapp.data.api.ApiHelper


/**
 * ViewModel provider factory to instantiate MainActivityViewModel.
 * Required given MainActivityViewModel has a non-empty constructor
 */

class MainActivityViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(MainActivityRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}