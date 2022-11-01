package com.droiddev26.sqliteandroidex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.droiddev26.sqliteandroidex.db.DBManager

class MainActivity : AppCompatActivity() {
    val dbManager = DBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val tvTest = findViewById<TextView>(R.id.tvTest)
        tvTest.text = ""
        dbManager.openDB()
        val dataList = dbManager.readDBData()
        for (item in dataList) {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }

    fun OnClickSave(view: View) {
        val tvTest = findViewById<TextView>(R.id.tvTest)
        tvTest.text = ""
        dbManager.openDB()
        val edTitle = findViewById<EditText>(R.id.edTitle)
        val edContent = findViewById<EditText>(R.id.edContent)
        dbManager.insertToDB(edTitle.text.toString(), edContent.text.toString())
        val dataList = dbManager.readDBData()
        for (item in dataList) {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDB()
    }
}