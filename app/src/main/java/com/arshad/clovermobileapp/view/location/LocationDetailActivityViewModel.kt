package com.arshad.clovermobileapp.view.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arshad.clovermobileapp.data.Resource
import com.arshad.clovermobileapp.view.dashboard.MainActivityRepository
import kotlinx.coroutines.Dispatchers

class LocationDetailActivityViewModel(private val locationDetailActivityRepository: LocationDetailActivityRepository) : ViewModel()  {

    fun getLocationDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = locationDetailActivityRepository.getLocationDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}