package com.example.mycollege

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DATABASE_NAME ="MyDb"
val TABLE_NAME ="mails"
class DbHelper(context:Context?) :SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable ="CREATE TABLE $TABLE_NAME (email TEXT PRIMARY KEY);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(mail:String){
        val db =writableDatabase
        var cv =ContentValues()
        cv.put("email",mail)
        db.insert(TABLE_NAME, null,cv)
    }

    fun readData():String{
        val db =readableDatabase
        val query ="SELECT * FROM $TABLE_NAME"
        val info =db.rawQuery(query,null)
        info.moveToFirst()
        val result=info.getString(0)
        return result
    }
}