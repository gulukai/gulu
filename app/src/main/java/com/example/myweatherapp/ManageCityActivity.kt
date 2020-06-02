package com.example.myweatherapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myweatherapp.common.Tag
import com.example.myweatherapp.common.getJson
import com.example.myweatherapp.data.CityData
import com.example.myweatherapp.dialog.MyDialog
import kotlinx.android.synthetic.main.activity_manage_city.*

class ManageCityActivity : AppCompatActivity() {

    private val cityList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_city)
        setSupportActionBar(toolbar_city)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getJson<CityData>("http://api.nieyu.com/weather/getCities",null){
            val list = it.data
            for (i in list.indices){
                cityList.add(list[i])
            }
            btn_manage.setOnClickListener {
                val dialog = MyDialog(this)
                dialog.show()
                dialog.setStyle { itt->
                    itt.setAdapter(ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,cityList))
                    itt.setOnItemClickListener { parent, _, position, _ ->
                        val  obj = parent.getItemAtPosition(position).toString()
                        Log.i(Tag,obj)
                        dialog.dismiss()
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return true
    }
}
