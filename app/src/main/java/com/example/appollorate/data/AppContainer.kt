package com.example.appollorate.data

import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val gson = GsonBuilder().setLenient().create()
    private val baseUrl = "https://www.appollorate.be"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
