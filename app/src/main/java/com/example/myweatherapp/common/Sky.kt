package com.example.myweatherapp.common

import com.example.myweatherapp.R


class Sky ( val icon: Int, val bg: Int , val bg_small : Int)

private val sky = mapOf(
    "晴" to Sky(R.drawable.ic_clear_day, R.drawable.bg_clear_day,R.drawable.bg_clear_day_small),
    "多云" to Sky(R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day,R.drawable.bg_partly_cloudy_day_small),
    "阴" to Sky(R.drawable.ic_cloudy, R.drawable.bg_cloudy,R.drawable.bg_cloudy_small),
    "大风" to Sky(R.drawable.ic_cloudy, R.drawable.bg_wind,R.drawable.bg_wind_small),
    "小雨" to Sky( R.drawable.ic_light_rain, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "中雨" to Sky( R.drawable.ic_moderate_rain, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "大雨" to Sky(R.drawable.ic_heavy_rain, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "暴雨" to Sky(R.drawable.ic_storm_rain, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "雷阵雨" to Sky( R.drawable.ic_thunder_shower, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "雨夹雪" to Sky( R.drawable.ic_sleet, R.drawable.bg_rain,R.drawable.bg_rain_small),
    "小雪" to Sky(R.drawable.ic_light_snow, R.drawable.bg_snow,R.drawable.bg_snow_small),
    "中雪" to Sky(R.drawable.ic_moderate_snow, R.drawable.bg_snow,R.drawable.bg_snow_small),
    "大雪" to Sky( R.drawable.ic_heavy_snow, R.drawable.bg_snow,R.drawable.bg_snow_small),
    "暴雪" to Sky( R.drawable.ic_heavy_snow, R.drawable.bg_snow,R.drawable.bg_snow_small),
    "冰雹" to Sky( R.drawable.ic_hail, R.drawable.bg_snow,R.drawable.bg_snow_small),
    "轻度雾霾" to Sky(R.drawable.ic_light_haze, R.drawable.bg_fog,R.drawable.bg_fog_small),
    "中度雾霾" to Sky( R.drawable.ic_moderate_haze, R.drawable.bg_fog,R.drawable.bg_fog_small),
    "重度雾霾" to Sky( R.drawable.ic_heavy_haze, R.drawable.bg_fog,R.drawable.bg_fog_small),
    "雾" to Sky(R.drawable.ic_fog, R.drawable.bg_fog,R.drawable.bg_fog_small),
    "浮沉" to Sky( R.drawable.ic_fog, R.drawable.bg_fog,R.drawable.bg_fog_small)
    )

fun getSky(skyMsg: String): Sky {
    return sky[skyMsg] ?: (sky["晴"] ?: error(""))
}