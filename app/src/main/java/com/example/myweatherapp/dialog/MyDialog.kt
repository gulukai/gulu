package com.example.myweatherapp.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.example.myweatherapp.R
import kotlinx.android.synthetic.main.dialoglayout.*


class MyDialog(context: Context) : Dialog(context) {


    private var style:((tv1:AutoCompleteTextView)->Unit)?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialoglayout)

        //设置dialog的宽度为屏幕宽度的百分之八十
        val m=window?.windowManager
        val d=m?.defaultDisplay
        val p=window?.attributes
        val size=Point()
        d?.getSize(size)
        p?.width=(size.x*0.8).toInt()
        window?.attributes=p

    }
    fun setStyle(style:((tv1:AutoCompleteTextView)->Unit)) {
        this.style=style
        this.style!!.invoke(autoText_dialog)
    }
}