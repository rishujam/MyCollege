package com.example.mycollege

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


const val DATABASE_NAME ="MyDb"
const val TABLE_NAME ="mails"
class DbHelper(context:Context?) :SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable ="CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT, name TEXT);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(mail:String,username:String){
        val db =writableDatabase
        var cv =ContentValues()
        cv.put("email",mail)
        cv.put("name",username)
        db.insert(TABLE_NAME, null,cv)
    }

    fun readMail():String{
        val db =readableDatabase
        val query ="SELECT email FROM $TABLE_NAME"
        val info =db.rawQuery(query,null)
        info.moveToFirst()
        return info.getString(0)
    }

    fun readName() :String{
        val db =readableDatabase
        val query = "SELECT name FROM $TABLE_NAME"
        val info=db.rawQuery(query,null)
        info.moveToLast()
        return info.getString(0)
    }
}