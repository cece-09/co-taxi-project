package com.example.prj1114

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class Act06Article : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act6_article)

        var sloc:TextView
        sloc = findViewById(R.id.textView9)
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

        try {
            var inFs: FileInputStream = openFileInput("file.txt")
            var txt = ByteArray(30)
            inFs.read(txt)
            var str = txt.toString(Charsets.UTF_8)
            Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
            inFs.close()
            sloc.text = str
        } catch (e:IOException){
            Toast.makeText(applicationContext,"No file",Toast.LENGTH_SHORT).show()
        }
    }
}