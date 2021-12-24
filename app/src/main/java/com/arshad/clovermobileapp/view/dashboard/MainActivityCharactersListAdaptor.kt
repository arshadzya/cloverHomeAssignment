package com.arshad.clovermobileapp.view.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.arshad.clovermobileapp.R
import com.arshad.clovermobileapp.data.model.LocationDetail
import com.arshad.clovermobileapp.data.model.Results
import com.arshad.clovermobileapp.view.location.LocationDetailActivity


//List<Results>
class MainActivityCharactersListAdaptor (private val characterList: List<Results>, val mContext: Context) : RecyclerView.Adapter<MainActivityCharactersListAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val adapterView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(adapterView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

            //OnBindViewHolder Method we are setting the value
            holder.mTextViewCharacterName.text = characterList.get(position).name
            holder.mTextViewStatus.text = mContext.resources.getString(R.string.status)+" "+characterList.get(position).status
            holder.mTextViewSpecies.text = mContext.resources.getString(R.string.species)+" "+characterList.get(position).species

            holder.mCardViewContainer.setOnClickListener {

                //todo key name should be from enum class, this is assignment and need to complete in short time
                //that's why I have written hard coded.

                val intent = Intent(mContext, LocationDetailActivity::class.java)
                intent.putExtra("Location_url", characterList.get(position).location.url)
                intent.putExtra("Location_image", characterList.get(position).image)
                mContext.startActivity(intent)

            }
    }

    override fun getItemCount() = characterList.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mTextViewCharacterName: TextView
        var mTextViewStatus: TextView
        var mTextViewSpecies: TextView
        var mCardViewContainer: CardView

        init {

            mTextViewCharacterName = view.findViewById(R.id.character_name_textview)
            mTextViewStatus = view.findViewById(R.id.status_textview)
            mTextViewSpecies = view.findViewById(R.id.species_textview)
            mCardViewContainer = view.findViewById(R.id.character_item_cardview)
        }




    }

}