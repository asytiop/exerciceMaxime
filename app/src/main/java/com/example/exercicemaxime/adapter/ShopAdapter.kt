package com.example.exercicemaxime.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercicemaxime.R
import com.example.exercicemaxime.model.Product
import com.example.exercicemaxime.model.Shop

class ShopAdapter(val ctx: Context, private val dataSet: MutableList<Shop>):
    RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    private val TAG: String = "ShopAdapater"
    private val context: Context

    init
    {
        context = ctx
    }

    class ShopViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val nameView: TextView
        val scheduleView: TextView
        val ratingView: TextView
        val placeView: TextView
        val logoView: ImageView

        init
        {
            nameView = mView.findViewById(R.id.text_name_shop)
            scheduleView = mView.findViewById(R.id.text_schedule)
            ratingView = mView.findViewById(R.id.text_ratings)
            placeView = mView.findViewById(R.id.text_place)
            logoView = mView.findViewById(R.id.image_shop)
        }

    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ShopAdapter.ShopViewHolder {
        // create a new view
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_near_you, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ShopViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {

        val item = dataSet[position]
        holder.nameView.text = item.title
        holder.scheduleView.text = item.openingHour.toString() + " - " + item.closingHour.toString()
        holder.ratingView.text = item.rating.toString()
        holder.placeView.text = item.distance.toString() + " km"

        Glide.with(context).clear(holder.logoView)

        if(item.logo != null && !item.logo!!.url.isNullOrEmpty())
        {
            Glide.with(context).load(Uri.parse(item.logo!!.url)).into(holder.logoView)
        }

    }

    fun updateListShops(newShops : List<Shop>)
    {
        dataSet.clear()
        dataSet.addAll(newShops)

        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size
}