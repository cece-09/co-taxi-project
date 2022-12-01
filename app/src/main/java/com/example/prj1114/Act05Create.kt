package com.example.prj1114

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.prj1114.AddressActivity.Companion.ADDRESS_REQUEST_CODE
import kotlinx.android.synthetic.main.act6_article.*
import kotlinx.android.synthetic.main.activity_act05_create.*
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class Act05Create : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act05_create)
        // access the items of the list
        val hours = resources.getStringArray(R.array.Hours)
        val minutes = resources.getStringArray(R.array.Minutes)
        val ampm = resources.getStringArray(R.array.AmPm)

        // access the spinner
        val h_spinner = findViewById<Spinner>(R.id.hourSpinner)
        val m_spinner = findViewById<Spinner>(R.id.minuteSpinner)
        val ampm_spinner = findViewById<Spinner>(R.id.amPmSpinner)


        if (h_spinner != null) {
            val houradapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, hours
            )
            h_spinner.adapter = houradapter

            h_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }



        if (m_spinner != null) {
            val minuteadapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, minutes
            )
            m_spinner.adapter = minuteadapter

            m_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }

        if (ampm_spinner != null) {
            val ampmadapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, ampm
            )
            ampm_spinner.adapter = ampmadapter

            ampm_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }

        button4.setOnClickListener {
            Intent(this, AddressActivity::class.java).apply {
                startActivityForResult(this, ADDRESS_REQUEST_CODE)
            }
        }
    }

    }




//CreateButton.setOnClickListener {
//            val intent = Intent(this, Act04List::class.java)
//            startActivity(intent)
//        }