package com.example.prj1114

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ViewFlipper

class Act01Regis : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act1_regis)

        var btnSendMail:Button
        var btnMailCheck:Button
        var btnRegis:Button
        var vFlipper:ViewFlipper
        var linearLayout1:LinearLayout

        btnSendMail = findViewById<Button>(R.id.btnSendMail)
        btnMailCheck = findViewById<Button>(R.id.btnMailCheck)
        btnRegis = findViewById<Button>(R.id.btnRegis)
        vFlipper = findViewById<ViewFlipper>(R.id.viewFlipper1)
        linearLayout1 = findViewById<LinearLayout>(R.id.linearlayout1)

        btnSendMail.setOnClickListener {
            if(linearLayout1.visibility == LinearLayout.INVISIBLE){
                linearLayout1.visibility = View.VISIBLE
            }
        }
        btnMailCheck.setOnClickListener {
            vFlipper.showNext()
        }
        btnRegis.setOnClickListener {
            var intent = Intent(this@Act01Regis,Act02Search::class.java)
            startActivity(intent)
        }
    }
}