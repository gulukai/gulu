package com.example.myweatherapp.common

import android.util.Log
import android.view.View
import com.google.gson.Gson
import java.lang.Exception

const val Tag = "Tag"


inline fun < reified T>getJson(url : String,view : View?, crossinline block:(data:T)->Unit){
    try {
        val commonTask = CommonTask()
        commonTask.url = url
        commonTask.progressBar = view
        commonTask.setCallback {
            val data= Gson().fromJson(it,T::class.java)
            block.invoke(data)
        }
        commonTask.execute()
    }catch (e:Exception){
        Log.i(Tag,"有问题")
    }
}