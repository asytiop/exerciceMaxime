package com.example.exercicemaxime.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewManager
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercicemaxime.R
import com.example.exercicemaxime.adapter.ProductAdapter
import com.example.exercicemaxime.adapter.ShopAdapter
import com.example.exercicemaxime.model.Product
import com.example.exercicemaxime.model.Shop
import com.example.exercicemaxime.webservice.ParseAPI
import com.example.exercicemaxime.webservice.ParseAPI.Companion.getAllProducts
import com.example.exercicemaxime.webservice.ParseAPI.Companion.getAllShops

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var textView: TextView
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var shopRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var shopAdapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_nbshops)

        productRecyclerView = findViewById(R.id.recycler_top_selling)
        shopRecyclerView = findViewById(R.id.recycler_near_you)

        productAdapter = ProductAdapter(this, mutableListOf())
        shopAdapter = ShopAdapter(this, mutableListOf())

        productRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        productRecyclerView.adapter = productAdapter
        shopRecyclerView.layoutManager = LinearLayoutManager(this)
        shopRecyclerView.adapter = shopAdapter


        getProducts()
        getShops()
    }

    private fun getProducts()
    {
        Log.d(TAG, "getProducts()")

        ParseAPI.getAllProducts { isSuccess, products ->

            if(isSuccess)
            {
                refreshProductRecycler(products)
            }
        }
    }

    private fun getShops()
    {
        Log.d(TAG, "getShops()")

        ParseAPI.getAllShops { isSuccess, shops ->

            if (isSuccess)
            {
                refreshShopRecycler(shops)
                textView.text = shopAdapter.itemCount.toString() + " shops"
            }
        }
    }

    private fun refreshProductRecycler(products: List<Product>?)
    {
        Log.d(TAG,"refreshProductRecycler()")

        if(!products.isNullOrEmpty())
        {
            val croppedProducts = products.take(10)
            productAdapter.updateListProducts(croppedProducts)
        }
    }

    private fun refreshShopRecycler(shops: List<Shop>?)
    {
        Log.d(TAG,"refreshShopRecycler()")

        if(!shops.isNullOrEmpty())
        {
            val croppedShops = shops.take(10)
            shopAdapter.updateListShops(croppedShops)
        }
    }
}