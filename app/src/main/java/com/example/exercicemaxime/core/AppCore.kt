package com.example.exercicemaxime.core

import android.content.Context
import com.example.exercicemaxime.model.Product
import com.example.exercicemaxime.model.Shop

object AppCore {

    const val TAG = "AppCore"

    var application: Context? = null

    var currentProduct: Product? = null
    var currentShop: Shop? = null

    fun init(context: Context)
    {
        application = context
    }

}