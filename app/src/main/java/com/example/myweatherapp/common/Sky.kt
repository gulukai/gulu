package com.example.myweatherapp.common

import com.example.myweatherapp.R


class Sky ( val icon: Int, val bg: Int)

private val sky = mapOf(
    "晴" to Sky(R.drawable.ic_clear_day, R.drawable.bg_clear_day),
    "多云" to Sky(R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day),
    "阴" to Sky(R.drawable.ic_cloudy, R.drawable.bg_cloudy),
    "大风" to Sky(R.drawable.ic_cloudy, R.drawable.bg_wind),
    "小雨" to Sky( R.drawable.ic_light_rain, R.drawable.bg_rain),
    "中雨" to Sky( R.drawable.ic_moderate_rain, R.drawable.bg_rain),
    "大雨" to Sky(R.drawable.ic_heavy_rain, R.drawable.bg_rain),
    "暴雨" to Sky(R.drawable.ic_storm_rain, R.drawable.bg_rain),
    "雷阵雨" to Sky( R.drawable.ic_thunder_shower, R.drawable.bg_rain),
    "雨夹雪" to Sky( R.drawable.ic_sleet, R.drawable.bg_rain),
    "小雪" to Sky(R.drawable.ic_light_snow, R.drawable.bg_snow),
    "中雪" to Sky(R.drawable.ic_moderate_snow, R.drawable.bg_snow),
    "大雪" to Sky( R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    "暴雪" to Sky( R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    "冰雹" to Sky( R.drawable.ic_hail, R.drawable.bg_snow),
    "轻度雾霾" to Sky(R.drawable.ic_light_haze, R.drawable.bg_fog),
    "中度雾霾" to Sky( R.drawable.ic_moderate_haze, R.drawable.bg_fog),
    "重度雾霾" to Sky( R.drawable.ic_heavy_haze, R.drawable.bg_fog),
    "雾" to Sky(R.drawable.ic_fog, R.drawable.bg_fog),
    "浮沉" to Sky( R.drawable.ic_fog, R.drawable.bg_fog)
    )

fun getSky(skyMsg: String): Sky {
    return sky[skyMsg] ?: sky["晴"]!!
}