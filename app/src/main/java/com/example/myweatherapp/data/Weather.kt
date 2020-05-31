package com.example.myweatherapp.data

data class Weather(
    val code: Int,
    val `data`: WeatherData,
    val message: String
)

data class WeatherData(
    val alert: List<Alert>,
    val area: List<List<String>>,
    val f3h: F3h,
    val historyWeather: HistoryWeather,
    val life: Life,
    val pm25: Pm25,
    val pubdate: String,
    val pubtime: String,
    val realtime: Realtime,
    val time: Int,
    val weather: List<WeatherXX>
)

data class Alert(
    val alarmPic1: String,
    val alarmPic2: String,
    val alarmTp1: String,
    val alarmTp2: String,
    val city: String,
    val content: String,
    val county: String,
    val originUrl: String,
    val province: String,
    val pubTime: String,
    val time: String
)

data class F3h(
    val `0`: X0,
    val `1`: X1,
    val `10`: X10,
    val `11`: X11,
    val `12`: X12,
    val `13`: X13,
    val `14`: X14,
    val `15`: X15,
    val `16`: X16,
    val `17`: X17,
    val `18`: X18,
    val `19`: X19,
    val `2`: X2,
    val `20`: X20,
    val `21`: X21,
    val `22`: X22,
    val `23`: X23,
    val `3`: X3,
    val `4`: X4,
    val `5`: X5,
    val `6`: X6,
    val `7`: X7,
    val `8`: X8,
    val `9`: X9,
    val city_code: String,
    val city_name: String,
    val pub_time: String
)

data class HistoryWeather(
    val history: History
)

data class Life(
    val date: String,
    val info: InfoX
)

data class Pm25(
    val aqi: List<Any>,
    val area: List<String>,
    val pm25: List<Any>
)

data class Realtime(
    val city_code: String,
    val city_name: String,
    val dataUptime: Int,
    val date: String,
    val moon: String,
    val time: String,
    val weather: WeatherX,
    val week: String,
    val wind: Wind
)

data class WeatherXX(
    val date: String,
    val info: InfoXX
)

data class X0(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X1(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X10(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X11(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X12(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X13(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X14(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X15(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X16(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X17(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X18(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X19(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X2(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X20(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X21(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X22(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X23(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X3(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X4(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X5(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X6(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X7(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X8(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class X9(
    val ja: String,
    val jb: String,
    val jc: String,
    val jd: String,
    val je: String,
    val jf: String,
    val jg: String
)

data class History(
    val `1`: X1X
)

data class X1X(
    val date: String,
    val info: Info
)

data class Info(
    val dawn: Any,
    val day: List<String>,
    val night: List<String>
)

data class InfoX(
    val chuanyi: List<String>,
    val daisan: List<String>,
    val diaoyu: List<String>,
    val ganmao: List<String>,
    val guomin: List<String>,
    val kongtiao: List<String>,
    val shushidu: List<String>,
    val wuran: List<String>,
    val xiche: List<String>,
    val yundong: List<String>,
    val ziwaixian: List<String>
)

data class WeatherX(
    val humidity: String,
    val img: String,
    val info: String,
    val temperature: String
)

data class Wind(
    val direct: String,
    val offset: String,
    val power: String,
    val windspeed: String
)

data class InfoXX(
    val dawn: List<String>,
    val day: List<String>,
    val night: List<String>
)