package com.example.appollorate

import android.app.Application
import com.example.appollorate.data.AppContainer
import com.example.appollorate.data.DefaultAppContainer

class AppolloRateApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
