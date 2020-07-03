package com.example.exercicemaxime.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        var cardView: CardView
        var addImageView: ImageView

        init
        {
            titleText = mView.findViewById(R.id.text_fruit_name)
            priceText = mView.findViewById(R.id.text_fruit_price)
            pictureImage = mView.findViewById(R.id.image_fruit)
            cardView = mView.findViewById(R.id.product_cardview)
            addImageView = mView.findViewById(R.id.image_fruit_add)
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
        holder.priceText.text = "$" + "%.2f".format(item.price)

        Glide.with(context).clear(holder.pictureImage)

        if(!item.picture!!.url.isNullOrEmpty())
        {
            Glide.with(context)
                .asBitmap()
                .load(Uri.parse(item.picture!!.url))
                .listener(object: RequestListener<Bitmap?> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        if (resource != null)
                        {
                            val p = Palette.from(resource).generate()
                            // Use generated instance
                            val color = p.getLightVibrantColor(ContextCompat.getColor(context, R.color.cardViewColor))
                            holder.cardView.setCardBackgroundColor(color)

                            var colorAdd = p.getVibrantColor(ContextCompat.getColor(context, R.color.colorAddImage))
                            holder.addImageView.setColorFilter(colorAdd)
                        }
                        return false
                    }
                })
                .into(holder.pictureImage)
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