package com.example.myweatherapp.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private var fruitList : ArrayList<*>?) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    constructor():this(null)

     inner class ViewHolder( view : View) : RecyclerView.ViewHolder(view)

    private var bindViewHolder : ((holder : ViewHolder, position : Int)->Unit)? = null
    var viewHolder : ((parent : ViewGroup, viewType : Int)->ViewHolder)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return viewHolder!!.invoke(parent, viewType)
    }

    override fun getItemCount(): Int {
        return fruitList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindViewHolder!!.invoke(holder , position)
    }

    class Builder{
        private val RecyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter()

        fun setData(data : ArrayList<*>) : Builder{
            RecyclerViewAdapter.fruitList = data
            return this
        }

        fun setOnBindViewHolder(bindViewHolder : (holder : ViewHolder,position : Int)->Unit) : Builder{
            RecyclerViewAdapter.bindViewHolder = bindViewHolder
            return this
        }

        fun setOnCreateViewHolder(viewHolder : (parent : ViewGroup , viewType : Int)->ViewHolder) : Builder{
            RecyclerViewAdapter.viewHolder = viewHolder
            return this
        }

        fun create () : RecyclerViewAdapter{
            return RecyclerViewAdapter
        }
    }


}