package com.arshad.clovermobileapp.view.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arshad.clovermobileapp.data.Resource
import kotlinx.coroutines.Dispatchers
/*
* This class is ViewModel class of MainActivity where we have to display list of characters
* this class provide live data after getting api
* */
class MainActivityViewModel(private val mainActivityRepository: MainActivityRepository) : ViewModel()  {

    fun getCharactersList(count: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainActivityRepository.getCharacters(count)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}