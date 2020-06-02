package com.example.myweatherapp.control

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myweatherapp.R
import kotlinx.android.synthetic.main.lifelayout.view.*

class LifeUi(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    init {
    LayoutInflater.from(context).inflate(R.layout.lifelayout,this,true)
    }

    private var style : ((img : ImageView, txt1 : TextView, txt2 : TextView)->Unit)? = null

    fun setStyle(style : ((img : ImageView , txt1 : TextView , txt2 : TextView)->Unit)){
        this.style = style
        style.invoke(Image_life , ganmao , ganmaoMessage)
    }
}