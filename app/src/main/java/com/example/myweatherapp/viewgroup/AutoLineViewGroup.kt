package com.example.weatherapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.adapter.AuToLineAdapter
import com.example.weatherapp.data.Position
import java.lang.Exception
import kotlin.math.max


class AutoLineViewGroup(context: Context, attrs:AttributeSet?) :ViewGroup(context,attrs){
    constructor(context: Context):this(context,null)

    private var adapter: AuToLineAdapter?=null

    var map= mutableMapOf<View, Position>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        map.clear()



        val widthMode=MeasureSpec.getMode(widthMeasureSpec)
        val widthSize=MeasureSpec.getSize(widthMeasureSpec)
        val heightMode=MeasureSpec.getMode(heightMeasureSpec)
        var heightSize=MeasureSpec.getSize(heightMeasureSpec)

        var height=0

        var top=0

        var lineWidth=paddingLeft

        var maxHeight=0
        var currentHeight=0






        for (i in 0 until childCount){
            val view=getChildAt(i)
            var leftMargin:Int
            var topMargin:Int
            try {
                val marginLayoutParams=view.layoutParams as MarginLayoutParams
                leftMargin=marginLayoutParams.leftMargin
                topMargin=marginLayoutParams.topMargin
            }catch (e:Exception){
                leftMargin=0
                topMargin=0
            }

            measureChild(view,widthMeasureSpec,heightMeasureSpec)
            if (view.measuredHeight>maxHeight){
                if (leftMargin+lineWidth+view.measuredWidth<=widthSize-paddingRight) {
                    maxHeight = view.measuredHeight+topMargin
                }
            }

            var position:Position
            if (leftMargin+lineWidth+view.measuredWidth>widthSize-paddingRight){
                currentHeight=height
                top+=maxHeight
                maxHeight=view.measuredHeight+topMargin
                height+=maxHeight
                lineWidth=paddingLeft
                position=Position(lineWidth+leftMargin,top+topMargin+paddingTop,lineWidth+view.measuredWidth+leftMargin,top+view.measuredHeight+topMargin+paddingTop)
                lineWidth=paddingLeft+view.measuredWidth+leftMargin
            }else{
                height= max(height,currentHeight+view.measuredHeight+topMargin)
                position=Position(lineWidth+leftMargin,top+topMargin+paddingTop,lineWidth+view.measuredWidth+leftMargin,top+view.measuredHeight+topMargin+paddingTop)
                lineWidth+=view.measuredWidth+leftMargin
            }
            map[view] = position
        }


        if (heightMode==MeasureSpec.AT_MOST){
            heightSize=height+paddingTop+paddingBottom
        }

        setMeasuredDimension(widthSize,heightSize)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount){
            val view=getChildAt(i)
            val position= map[view]
            view.layout(position!!.left,position!!.top,position!!.right,position!!.bottom)
        }
    }


    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }

    fun setAdapter(adapter:AuToLineAdapter?){
        this.adapter=adapter
        removeAllViews()
        if (adapter!=null){
            for (i in 0 until adapter.dataList.size ){
                val view=adapter.getView(this,adapter.dataList,i)
                addView(view,i)
            }
        }
    }

    fun dataChange(){
        if (adapter!=null){
            val view=adapter!!.getView(this,adapter!!.dataList,adapter!!.dataList.size-1)
            addView(view,0)
        }

    }

}