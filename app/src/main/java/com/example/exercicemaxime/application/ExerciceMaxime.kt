package com.example.exercicemaxime.application

import android.app.Application
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.util.Log
import com.example.exercicemaxime.core.AppCore
import com.example.exercicemaxime.core.PARSE
import com.example.exercicemaxime.model.Product
import com.example.exercicemaxime.model.Shop
import com.parse.Parse
import com.parse.ParseObject

class ExerciceMaxime: Application() {

    companion object
    {
        const val TAG = "TestApp"
        var ctx: Context? = null
    }


    override fun onCreate()
    {
        super.onCreate()
        ctx = applicationContext

        initSDK()
    }


    fun initSDK()
    {
        Log.d(TAG, "initSDK()")

        ParseObject.registerSubclass(Product::class.java)
        ParseObject.registerSubclass(Shop::class.java)

        // PARSE
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(PARSE.APPLICATION_ID) // if desired
                .clientKey(PARSE.CLIENT_KEY)
                .server(PARSE.SERVER)
                .enableLocalDataStore()
                .build()
        )

        AppCore.init(ctx!!)
    }
}