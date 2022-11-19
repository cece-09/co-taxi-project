package com.example.prj1114

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_act05_create.*
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class Act05Create : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act05_create)


        CreateButton.setOnClickListener {
            val intent = Intent(this, Act04List::class.java)
            startActivity(intent)
        }


    }
}