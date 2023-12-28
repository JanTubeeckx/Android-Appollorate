package com.example.appollorate.data

import android.content.Context
import com.example.appollorate.api.inventoryfield.InventoryFieldApiService
import com.example.appollorate.data.database.AppDatabase
import com.example.appollorate.data.inventoryfield.CachingInventoryFieldRepository
import com.example.appollorate.data.inventoryfield.InventoryFieldRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer {
    val inventoryFieldRepository: InventoryFieldRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(intercepter)
    }.build()

    private val gson = GsonBuilder().setLenient().create()
    private val baseUrl = "https://inventappwebservices.azurewebsites.net"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val inventoryFieldRetrofitService: InventoryFieldApiService by lazy {
        retrofit.create(InventoryFieldApiService::class.java)
    }

    override val inventoryFieldRepository: InventoryFieldRepository by lazy {
        CachingInventoryFieldRepository(AppDatabase.getDataBase(context = context).inventoryFieldDao(), inventoryFieldRetrofitService)
    }
}
