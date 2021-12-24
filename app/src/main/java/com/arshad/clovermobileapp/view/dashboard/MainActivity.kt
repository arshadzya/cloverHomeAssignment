package com.arshad.clovermobileapp.view.dashboard


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arshad.clovermobileapp.R
import com.arshad.clovermobileapp.data.Status
import com.arshad.clovermobileapp.data.api.ApiHelper
import com.arshad.clovermobileapp.data.api.RetrofitBuilder
import com.arshad.clovermobileapp.data.model.Results
import com.arshad.clovermobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityCharactersListAdaptor: MainActivityCharactersListAdaptor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mLayoutManagerNews: RecyclerView.LayoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.mainActivityRecycleview.layoutManager = mLayoutManagerNews
        binding.mainActivityRecycleview.setHasFixedSize(true)

        //this method is used for setup View Model
        setupViewModel()

        //this method is used for network call method
        setupObservers(20)

        //
    }




    /*This function is used for creating reference of ViewModel
    * Once reference is created will use any where within this activity
    * */
    private fun setupViewModel() {
        mainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }




    /*
    * This method is used for observing outputs of API and if API is successfully
    * return values will start calling adaptor for displaying records.
    * */
    private fun setupObservers(count: Int) {

        mainActivityViewModel.getCharactersList(count).observe(this, Observer {
            it?.let { resource ->


                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data.also { characters ->
                            displayCharacterList(resource.data!!.results)
                            //System.out.println("Character list "+resource.data!!.results.size)
                        }


                    }
                    Status.ERROR -> {
                        //  System.out.println("Registration error"+resource.message)
                        Toast.makeText(this@MainActivity,getString(R.string.error), Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        System.out.println("Registration loading")
                    }
                }
            }
        })
    }

    /*
    * This method is used for display data
    * calling adaptor class
    * */
    private fun displayCharacterList(charactersList:  List<Results>){

        mainActivityCharactersListAdaptor =
            MainActivityCharactersListAdaptor(charactersList, this)
        binding.mainActivityRecycleview.adapter = mainActivityCharactersListAdaptor
    }
}