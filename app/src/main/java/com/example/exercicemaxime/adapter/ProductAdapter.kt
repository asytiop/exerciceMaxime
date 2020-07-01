package com.example.exercicemaxime.adapter

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercicemaxime.R
import com.example.exercicemaxime.model.Product
import kotlinx.android.synthetic.main.item_top_selling.view.*

class ProductAdapter(val ctx: Context , private val dataSet: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    private val TAG: String = "ProductAdapter"

    val context: Context

    init
    {
        context = ctx
    }
    inner class ProductViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
    {
        val titleText: TextView
        val priceText: TextView
        val pictureImage: ImageView

        init
        {
            titleText = mView.findViewById(R.id.text_fruit_name)
            priceText = mView.findViewById(R.id.text_fruit_price)
            pictureImage = mView.findViewById(R.id.image_fruit)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProductAdapter.ProductViewHolder {

        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_selling, parent, false) as View

        return ProductViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val item = dataSet[position]
        holder.titleText.text = item.title
        holder.priceText.text = "$" + item.price.toString()

        Glide.with(context).clear(holder.pictureImage)

        if(item.picture != null && !item.picture!!.url.isNullOrEmpty())
        {
            Glide.with(context).load(Uri.parse(item.picture!!.url)).into(holder.pictureImage)
        }

    }

    fun updateListProducts(newProducts: List<Product>)
    {
        dataSet.clear()
        dataSet.addAll(newProducts)

        notifyDataSetChanged()
    }


    override fun getItemCount() = dataSet.size
}