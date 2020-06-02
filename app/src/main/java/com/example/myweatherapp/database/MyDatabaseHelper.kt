package com.example.myweatherapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context,name : String , version : Int ) : SQLiteOpenHelper(context,name ,null,version) {
    private val createCityName = "create table cityName("+
            "id integer primary key autoincrement,"+
            "city_name text)"

    private val createCityMessage = "create table CityMessage("+
            "id integer primary key autoincrement,"+
            "city_name text,"+
            "city_temperature integer,"+
            "city_message text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createCityName)
        db.execSQL(createCityMessage)
        Toast.makeText(context,"建表成功",Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion<=1){
            db.execSQL(createCityName)
            db.execSQL(createCityMessage)
        }
    }

}