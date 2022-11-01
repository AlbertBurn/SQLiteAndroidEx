package com.droiddev26.sqliteandroidex.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DBManager(context: Context) {
    val dbHelper = MyDBHelper(context)
    var db: SQLiteDatabase ?= null

    fun openDB() {
        db = dbHelper.writableDatabase
    }

    fun insertToDB(title: String, content: String) {
        // Gets the data repository in write mode
        val db = dbHelper.writableDatabase

// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DBNameClass.COLUMN_NAME_TITLE, title)
            put(DBNameClass.COLUMN_NAME_CONTENT, content)
        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(DBNameClass.TABLE_NAME, null, values)
    }

    fun readDBData() : ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(
            DBNameClass.TABLE_NAME,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = getString(getColumnIndexOrThrow(DBNameClass.COLUMN_NAME_TITLE))
                //val dataText2 = getString(getColumnIndexOrThrow(DBNameClass.COLUMN_NAME_CONTENT))
                dataList.add(dataText)
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDB(){
        dbHelper.close()
    }
}