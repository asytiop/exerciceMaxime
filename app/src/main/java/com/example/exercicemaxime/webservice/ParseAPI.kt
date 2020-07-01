package com.example.exercicemaxime.webservice

import android.util.Log
import com.example.exercicemaxime.model.Product
import com.example.exercicemaxime.model.Shop
import com.parse.FindCallback
import com.parse.ParseQuery

class ParseAPI {

    companion object
    {
        const val TAG: String = "ParseAPI"

        fun getAllProducts(listener: (isSuccess: Boolean, products: List<Product>?) -> Unit)
        {
            Log.d(TAG, "getAllProducts()")

            ParseQuery.getQuery(Product::class.java)
                .include("picture")
                .fromNetwork()
                .orderByDescending("createdAt")
                .setLimit(500)
                .findInBackground(FindCallback { results, error ->

                    if (error == null)
                    {
                        if (!results.isNullOrEmpty())
                        {
                            Log.d(TAG, "getAllProducts() :: nb products = " + results.size)
                        }
                        else
                        {
                            Log.d(TAG, "getAllProducts() :: no products")
                        }


                        listener.invoke(true, results)
                    }
                    else
                    {
                        Log.d( TAG,"getAllProducts() :: callback error (" + error.code + ") = " + error.localizedMessage)
                        listener.invoke(false, null)
                    }

                })

        }

        fun getAllShops(listener: (isSuccess: Boolean, shops: List<Shop>?) -> Unit)
        {
            Log.d(TAG, "getAllShopss()")

            ParseQuery.getQuery(Shop::class.java)
                .include("picture")
                .fromNetwork()
                .orderByDescending("createdAt")
                .setLimit(500)
                .findInBackground(FindCallback { results, error ->

                    if (error == null)
                    {
                        if (!results.isNullOrEmpty())
                        {
                            Log.d(TAG, "getAllShop() :: nb shops = " + results.size)
                        }
                        else
                        {
                            Log.d(TAG, "getAllShop() :: no shops")
                        }


                        listener.invoke(true, results)
                    }
                    else
                    {
                        Log.d( TAG,"getAllShop() :: callback error (" + error.code + ") = " + error.localizedMessage)
                        listener.invoke(false, null)
                    }

                })

        }

    }
}