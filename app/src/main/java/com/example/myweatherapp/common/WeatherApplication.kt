package com.example.weatherapp.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "Elliw4XmahAWtUDq"  //彩云API申请的令牌值

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}