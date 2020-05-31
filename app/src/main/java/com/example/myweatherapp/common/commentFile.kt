package com.example.myweatherapp.common

import android.view.View
import com.google.gson.Gson

const val Tag = "Tag"


inline fun < reified T>getJson(url : String,view : View, crossinline block:(data:T)->Unit){
    val commonTask = CommonTask()
    commonTask.url = url
    commonTask.progressBar = view
    commonTask.setCallback {
        val data= Gson().fromJson(it,T::class.java)
        block.invoke(data)
    }
    commonTask.execute()
}