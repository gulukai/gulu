package com.example.weatherapp.adapter

import android.view.View
import android.view.ViewGroup

abstract class AuToLineAdapter(var dataList:ArrayList<String>){
//    var dataList=dataList
     abstract fun getView(parent: ViewGroup, data:ArrayList<String>, position:Int):View
    fun change(data:ArrayList<String>){
        this.dataList=data
    }
}