package com.example.myweatherapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.content.contentValuesOf
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myweatherapp.adapter.RecyclerViewAdapter
import com.example.myweatherapp.common.getJson
import com.example.myweatherapp.common.getSky
import com.example.myweatherapp.data.AllDay
import com.example.myweatherapp.data.DaysData
import com.example.myweatherapp.data.Weather
import com.example.myweatherapp.database.MyDatabaseHelper
import com.example.weatherapp.adapter.AuToLineAdapter
import com.example.weatherapp.adapter.MyLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.allday.view.*
import kotlinx.android.synthetic.main.days.view.*
import kotlinx.android.synthetic.main.txtlayout.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var url: String = "https://widget.cifuwu.com/weather"
    private val weatherList = ArrayList<AllDay>()
    private val daysList = ArrayList<DaysData>()
    private val txtList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.home)
        }

        val dbHelper = MyDatabaseHelper(this, "city.db", 1)
        dbHelper.writableDatabase
        getMessage()
        val adapter = object : AuToLineAdapter(txtList) {
            override fun getView(parent: ViewGroup, data: ArrayList<String>, position: Int): View {
                val view = LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.txtlayout, parent, false)
                view.text_textLayout.text = data[position]
                view.setOnClickListener {
                    url = "https://widget.cifuwu.com/weather/?city=${view.text_textLayout.text}"
                    getMessage()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                return view
            }
        }
        group_main.setAdapter(adapter)
        val db = dbHelper.writableDatabase
        val cursor = db.query("cityName", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val cityName = cursor.getString(cursor.getColumnIndex("city_name"))
            txtList.add(cityName)
            adapter.change(txtList)
            group_main.dataChange()
        }
        cursor.close()
        edText_main.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                url = "https://widget.cifuwu.com/weather/?city=${edText_main.text}"
                val value = contentValuesOf(
                    "city_name" to edText_main.text.toString()
                )
                db.delete("cityName", "city_name=?", arrayOf(edText_main.text.toString()))
                db.insert("cityName", null, value)
                txtList.add(edText_main.text.toString())
                adapter.change(txtList)
                group_main.dataChange()
                getMessage()
                edText_main.setText("")
                drawerLayout.closeDrawer(GravityCompat.START)
                return@setOnEditorActionListener false
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            R.id.administration -> {
                val intentObj = Intent(this, ManageCityActivity::class.java)
//                startActivity(intentObj)
                startActivityForResult(intentObj,1)
            }
        }
        return true
    }

    private fun getMessage() {
        daysList.clear()
        weatherList.clear()
        getJson<Weather>(url, progressbar_main) {
            load_main.text = it.data.area[2][0]
            textTem_main.text = it.data.realtime.weather.temperature
            textMessage_main.text = it.data.realtime.weather.info
            Glide.with(this).load(getSky(it.data.weather[0].info.day[1]).bg).into(imageView_main)
            textAQI_main.text = it.data.pm25.aqi[0].toString()

            for (i in 0..23) {
                val string1 = when (i) {
                    0 -> {
                        it.data.f3h.`0`.ja
                    }
                    1 -> {
                        it.data.f3h.`1`.ja
                    }
                    2 -> {
                        it.data.f3h.`2`.ja
                    }
                    3 -> {
                        it.data.f3h.`3`.ja
                    }
                    4 -> {
                        it.data.f3h.`4`.ja
                    }
                    5 -> {
                        it.data.f3h.`5`.ja
                    }
                    6 -> {
                        it.data.f3h.`6`.ja
                    }
                    7 -> {
                        it.data.f3h.`7`.ja
                    }
                    8 -> {
                        it.data.f3h.`8`.ja
                    }
                    9 -> {
                        it.data.f3h.`9`.ja
                    }
                    10 -> {
                        it.data.f3h.`10`.ja
                    }
                    11 -> {
                        it.data.f3h.`11`.ja
                    }
                    12 -> {
                        it.data.f3h.`12`.ja
                    }
                    13 -> {
                        it.data.f3h.`13`.ja
                    }
                    14 -> {
                        it.data.f3h.`14`.ja
                    }
                    15 -> {
                        it.data.f3h.`15`.ja
                    }
                    16 -> {
                        it.data.f3h.`16`.ja
                    }
                    17 -> {
                        it.data.f3h.`17`.ja
                    }
                    18 -> {
                        it.data.f3h.`18`.ja
                    }
                    19 -> {
                        it.data.f3h.`19`.ja
                    }
                    20 -> {
                        it.data.f3h.`20`.ja
                    }
                    21 -> {
                        it.data.f3h.`21`.ja
                    }
                    22 -> {
                        it.data.f3h.`22`.ja
                    }
                    else -> {
                        it.data.f3h.`23`.ja
                    }
                }

                val string2 = when (i) {
                    0 -> {
                        it.data.f3h.`0`.jb
                    }
                    1 -> {
                        it.data.f3h.`1`.jb
                    }
                    2 -> {
                        it.data.f3h.`2`.jb
                    }
                    3 -> {
                        it.data.f3h.`3`.jb
                    }
                    4 -> {
                        it.data.f3h.`4`.jb
                    }
                    5 -> {
                        it.data.f3h.`5`.jb
                    }
                    6 -> {
                        it.data.f3h.`6`.jb
                    }
                    7 -> {
                        it.data.f3h.`7`.jb
                    }
                    8 -> {
                        it.data.f3h.`8`.jb
                    }
                    9 -> {
                        it.data.f3h.`9`.jb
                    }
                    10 -> {
                        it.data.f3h.`10`.jb
                    }
                    11 -> {
                        it.data.f3h.`11`.jb
                    }
                    12 -> {
                        it.data.f3h.`12`.jb
                    }
                    13 -> {
                        it.data.f3h.`13`.jb
                    }
                    14 -> {
                        it.data.f3h.`14`.jb
                    }
                    15 -> {
                        it.data.f3h.`15`.jb
                    }
                    16 -> {
                        it.data.f3h.`16`.jb
                    }
                    17 -> {
                        it.data.f3h.`17`.jb
                    }
                    18 -> {
                        it.data.f3h.`18`.jb
                    }
                    19 -> {
                        it.data.f3h.`19`.jb
                    }
                    20 -> {
                        it.data.f3h.`20`.jb
                    }
                    21 -> {
                        it.data.f3h.`21`.jb
                    }
                    22 -> {
                        it.data.f3h.`22`.jb
                    }
                    else -> {
                        it.data.f3h.`23`.jb
                    }
                }
                weatherList.add(
                    AllDay(
                        "$i:00",
                        getSky(string1).icon,
                        string2
                    )
                )
            }
            val num = it.data.weather.size
            for (i in 0 until num) {
                daysList.add(
                    DaysData(
                        it.data.weather[i].date,
                        getWeek(it.data.weather[i].date),
                        getSky(it.data.weather[i].info.day[1]).icon,
                        it.data.weather[i].info.dawn[2],
                        it.data.weather[i].info.day[2]
                    )
                )
            }


            allDayRec_main.adapter = RecyclerViewAdapter.Builder().setData(weatherList)
                .setOnCreateViewHolder { parent, _ ->
                    return@setOnCreateViewHolder RecyclerViewAdapter().ViewHolder(
                        LayoutInflater.from(
                            parent.context
                        ).inflate(R.layout.allday, null, false)
                    )
                }.setOnBindViewHolder { holder, position ->
                    holder.itemView.time_allDay.text = weatherList[position].dayTime
                    Glide.with(this).load(weatherList[position].dayIcon)
                        .into(holder.itemView.img_allDay)
                    holder.itemView.temperature_allDay.text = weatherList[position].dayTem
                }.create()
            allDayRec_main.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            daysRec_main.adapter =
                RecyclerViewAdapter.Builder().setData(daysList).setOnCreateViewHolder { parent, _ ->
                    return@setOnCreateViewHolder RecyclerViewAdapter().ViewHolder(
                        LayoutInflater.from(
                            parent.context
                        ).inflate(R.layout.days, parent, false)
                    )
                }.setOnBindViewHolder { holder, position ->
                    holder.itemView.number_days.text = daysList[position].date
                    holder.itemView.date_days.text = daysList[position].week
                    Glide.with(this).load(daysList[position].icon).into(holder.itemView.img_days)
                    holder.itemView.min_days.text = daysList[position].min
                    holder.itemView.max_days.text = daysList[position].max
                }.create()
            daysRec_main.layoutManager = MyLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            life1.setStyle { img, txt1, txt2 ->
                img.setImageResource(R.drawable.ic_coldrisk)
                txt1.text = "感冒"
                txt2.text = it.data.life.info.ganmao[0]
            }
            life2.setStyle { img, txt1, txt2 ->
                img.setImageResource(R.drawable.ic_dressing)
                txt1.text = "穿衣"
                txt2.text = it.data.life.info.chuanyi[0]
            }
            life3.setStyle { img, txt1, txt2 ->
                img.setImageResource(R.drawable.ic_ultraviolet)
                txt1.text = "紫外线"
                txt2.text = it.data.life.info.ziwaixian[0]
            }
            life4.setStyle { img, txt1, txt2 ->
                img.setImageResource(R.drawable.ic_carwashing)
                txt1.text = "洗车"
                txt2.text = it.data.life.info.xiche[0]
            }
        }
    }

    private fun getWeek(pTime: String): String {
        var week = ""
        val format = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.time = format.parse(pTime)!!
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (c[Calendar.DAY_OF_WEEK] == 1) {
            week += "星期天"
        }
        if (c[Calendar.DAY_OF_WEEK] == 2) {
            week += "星期一"
        }
        if (c[Calendar.DAY_OF_WEEK] == 3) {
            week += "星期二"
        }
        if (c[Calendar.DAY_OF_WEEK] == 4) {
            week += "星期三"
        }
        if (c[Calendar.DAY_OF_WEEK] == 5) {
            week += "星期四"
        }
        if (c[Calendar.DAY_OF_WEEK] == 6) {
            week += "星期五"
        }
        if (c[Calendar.DAY_OF_WEEK] == 7) {
            week += "星期六"
        }
        return week
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val ralData = data?.extras
        when(requestCode){
            1->{
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val getData = ralData?.getString("city_name")
                        url = "https://widget.cifuwu.com/weather/?city=${getData}"
                        getMessage()
                    }
                }
            }
        }
    }
}
