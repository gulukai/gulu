package com.example.myweatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.adapter.RecyclerViewAdapter
import com.example.myweatherapp.common.Tag
import com.example.myweatherapp.common.getJson
import com.example.myweatherapp.common.getSky
import com.example.myweatherapp.data.CityData
import com.example.myweatherapp.data.CityTemperatureMessage
import com.example.myweatherapp.data.SpeacesItemDecoration
import com.example.myweatherapp.data.Weather
import com.example.myweatherapp.database.MyDatabaseHelper
import com.example.myweatherapp.dialog.MyDialog
import kotlinx.android.synthetic.main.activity_manage_city.*
import kotlinx.android.synthetic.main.city_message_layout.view.*

class ManageCityActivity : AppCompatActivity() {

    private val cityList = ArrayList<String>()
    private var url: String = "https://widget.cifuwu.com/weather"
    private val cityTemperatureMessageList = ArrayList<CityTemperatureMessage>()
    private val dbHelper = MyDatabaseHelper(this, "city.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_city)
        setSupportActionBar(toolbar_city)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val db = dbHelper.writableDatabase

        getJson<CityData>("http://api.nieyu.com/weather/getCities", null) {
            val list = it.data
            for (i in list.indices) {
                cityList.add(list[i])
            }
            btn_manage.setOnClickListener {
                val dialog = MyDialog(this)
                dialog.show()
                dialog.setStyle { itt ->
                    itt.setAdapter(
                        ArrayAdapter(
                            this,
                            R.layout.support_simple_spinner_dropdown_item,
                            cityList
                        )
                    )
                    itt.setOnItemClickListener { parent, _, position, _ ->
                        val obj = parent.getItemAtPosition(position).toString()
                        url = "https://widget.cifuwu.com/weather/?city=${obj}"
                        getMessage()

                        dialog.dismiss()
                    }
                }
            }
        }
        val cursor = db.query("CityMessage", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val cityName = cursor.getString(cursor.getColumnIndex("city_name"))
            Log.i(Tag,cityName)
            url = "https://widget.cifuwu.com/weather/?city=${cityName}"
            getJson<Weather>(url, null) {
                val city = it.data.realtime.city_name
                val message = it.data.realtime.weather.info
                val temperature = it.data.realtime.weather.temperature
                cityTemperatureMessageList.add(
                    CityTemperatureMessage(
                        city,
                        temperature,
                        message
                    )
                )
                setAdapter()
            }
        }
        cursor.close()
        recyclerView_manage.addItemDecoration(SpeacesItemDecoration(10))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun getMessage() {
        val db = dbHelper.writableDatabase
        getJson<Weather>(url, null) {
            val city = it.data.realtime.city_name
            val message = it.data.realtime.weather.info
            val temperature = it.data.realtime.weather.temperature
            cityTemperatureMessageList.add(
                CityTemperatureMessage(
                    city,
                    temperature,
                    message
                )
            )
            recyclerView_manage.adapter?.notifyItemInserted(cityTemperatureMessageList.size)
            recyclerView_manage.scrollToPosition(cityTemperatureMessageList.size)
            val value = contentValuesOf(
                "city_name" to city,
                "city_temperature" to null,
                "city_message" to null
            )
            db.delete("CityMessage" , "city_name=?", arrayOf(city))
            db.insert("CityMessage" , null , value)
        }
    }

    private fun setAdapter(){
        recyclerView_manage.adapter =
            RecyclerViewAdapter.Builder().setData(cityTemperatureMessageList)
                .setOnCreateViewHolder { parent, _ ->
                    return@setOnCreateViewHolder RecyclerViewAdapter().ViewHolder(
                        LayoutInflater.from(
                            parent.context
                        ).inflate(R.layout.city_message_layout, null, false)
                    )
                }.setOnBindViewHolder { holder, position ->
                    holder.itemView.text_message.text = cityTemperatureMessageList[position].City_Name
                    holder.itemView.text_temperature.text =
                        cityTemperatureMessageList[position].City_Temperature
                    holder.itemView.text_temperature_message.text =
                        cityTemperatureMessageList[position].City_Message
                    holder.itemView.linearLayout.setBackgroundResource(getSky(cityTemperatureMessageList[position].City_Message).bg_small)
                    holder.itemView.setOnClickListener {
                        val intentObj = Intent(this,MainActivity::class.java)
                        val cityName = cityTemperatureMessageList[position].City_Name
                        val ralData = Bundle()
                        ralData.putString("city_name",cityName)
                        intentObj.putExtras(ralData)
                        setResult(Activity.RESULT_OK,intentObj)
                        finish()
                    }
                }.create()
        recyclerView_manage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
