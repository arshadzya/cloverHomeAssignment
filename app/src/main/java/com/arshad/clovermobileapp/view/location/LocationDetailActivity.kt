package com.arshad.clovermobileapp.view.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arshad.clovermobileapp.R
import com.arshad.clovermobileapp.data.Status
import com.arshad.clovermobileapp.data.api.ApiHelper
import com.arshad.clovermobileapp.data.api.RetrofitBuilder
import coil.load
import com.arshad.clovermobileapp.data.model.LocationDetail
import com.arshad.clovermobileapp.databinding.ActivityDetailedBinding

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var locationDetailActivityViewModel: LocationDetailActivityViewModel
    private lateinit var binding: ActivityDetailedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //View binding
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val image = intent.getStringExtra("Location_image")
        val locationUrl = intent.getStringExtra("Location_url")

        var locationId = locationUrl!!.split("/")[locationUrl.split("/").size-1]



        //loading Image through COIL Library
        binding.imageView.load(image)


        setupViewModel()
        setupObservers(Integer.parseInt(locationId))
    }



    /*This function is used for creating reference of ViewModel
   * Once reference is created will use any where within this activity
   * */
    private fun setupViewModel() {
        locationDetailActivityViewModel = ViewModelProvider(
            this,
            LocationDetailActivityViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(LocationDetailActivityViewModel::class.java)
    }


    /*
    * This method is used for observing outputs of API and if API is successfully
    * return values will start calling adaptor for displaying records.
    * */
    private fun setupObservers(id: Int) {

        locationDetailActivityViewModel.getLocationDetail(id).observe(this, Observer {
            it?.let { resource ->


                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data.also { locationDetail ->
                            displayRecord(locationDetail)
                            //System.out.println("Location Residents list "+resource.data!!.residents.size)
                        }


                    }
                    Status.ERROR -> {
                        //  System.out.println("Registration error"+resource.message)
                        Toast.makeText(this@LocationDetailActivity,getString(R.string.error), Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        System.out.println("Registration loading")
                    }
                }
            }
        })
    }

    /*
    * This method is used for display details of Locations.
    * */
    private fun displayRecord(locationDetail : LocationDetail?){

        binding.locationNameTextview.text = locationDetail!!.name
        binding.typeTextview.text = "Location Type: "+ locationDetail.type
        binding.dimentionTextview.text = "Dimension: "+ locationDetail.dimension
        binding.residentsTextview.text = "No of Residents: "+ locationDetail.residents.size

    }
}