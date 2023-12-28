package com.example.appollorate.data

import android.content.Context

interface AppContainer

class DefaultAppContainer(private val context: Context) : AppContainer
