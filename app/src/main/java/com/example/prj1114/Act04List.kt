package com.example.prj1114

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Act04List : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act4_list)

        var btn1:Button
        var btn2:Button
        var btn3:Button

        btn1 = findViewById(R.id.btnList1)
        btn2 = findViewById(R.id.btnList2)
        btn3 = findViewById(R.id.btnList3)

        btn1.setOnClickListener {
            var intent = Intent(this@Act04List,Act06Article::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            var intent = Intent(this@Act04List,Act06Article::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            var intent = Intent(this@Act04List,Act05Create::class.java)
            startActivity(intent)
        }
    }
}