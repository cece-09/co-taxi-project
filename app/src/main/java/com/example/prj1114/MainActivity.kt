package com.example.prj1114

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var button1:Button
    lateinit var button2:Button
    lateinit var button3:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener {
//            var intent = Intent(applicationContext,Act01Regis::class.java)
            var intent = Intent(this@MainActivity,Act01Regis::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            var intent = Intent(this@MainActivity,Act02Search::class.java)
            startActivity(intent)
        }
    }
}