package com.example.prj1114

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button

class Act02Search : Activity(){
    lateinit var dialogView:View
    var aaa:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.act2_search)

        var buttonSetTime:Button
        var buttonGps:Button
        var buttonSetPlace1:Button
        var buttonSetPlace2:Button
        var str1:AutoCompleteTextView
        var buttonSearch:Button

        buttonSetTime = findViewById(R.id.btnSetTime)
        buttonGps = findViewById(R.id.btnGPS)
        buttonSetPlace1 = findViewById(R.id.btnSetPlace1)
        buttonSetPlace2 = findViewById(R.id.btnSetPlace2)
        str1 = findViewById(R.id.autoCompleteTextView)
        buttonSearch = findViewById(R.id.btnSearch)

        str1.setText(aaa.toString())

        buttonSetTime.setOnClickListener {
            dialogView = View.inflate(this@Act02Search,R.layout.dialog1,null)
            var dlg = AlertDialog.Builder(this@Act02Search)
            dlg.setView(dialogView)
            dlg.setPositiveButton("ok",null)
            dlg.setNegativeButton("cancel",null)
            dlg.show()
        }
        buttonGps.setOnClickListener {
            var dlg = AlertDialog.Builder(this@Act02Search)
            dlg.setMessage("MI90")
            dlg.setPositiveButton("ok",null)
            dlg.show()
        }
        buttonSetPlace1.setOnClickListener {
            var intent = Intent(this@Act02Search,Act03Map::class.java)
            startActivityForResult(intent, aaa)
        }
        buttonSetPlace2.setOnClickListener {
            var intent = Intent(this@Act02Search,Act03Map::class.java)
            startActivity(intent)
        }
        buttonSearch.setOnClickListener {
            var intent = Intent(this@Act02Search,Act04List::class.java)
            startActivity(intent)
        }
    }
}