package com.example.prj1114

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class Act05Create : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act5_create)

        var btn:Button
        btn = findViewById(R.id.btnCreate)

        btn.setOnClickListener {
            //createTeam
            var outFs : FileOutputStream = openFileOutput("file.txt", Context.MODE_PRIVATE)
            var objectOutputStream : ObjectOutputStream = ObjectOutputStream(outFs)

            var tid:Int = 1123
            var time:Button = findViewById(R.id.btnSetTime)
            var uMax:Int = 4
            var sta:Int = 0
            var pl1:Button = findViewById(R.id.btnSetPlace1)
            var pl2:Button = findViewById(R.id.btnSetPlace2)
            var uid:Int = 3321

            var teaminfo = Team(tid, time.text.toString(), uMax, sta, pl1.text.toString(), pl2.text.toString(),uid)

            objectOutputStream.writeObject(teaminfo)
            objectOutputStream.close()
            outFs.close()
            Toast.makeText(applicationContext,"create file.txt",Toast.LENGTH_SHORT).show()

            var intent = Intent(this@Act05Create,Act06Article::class.java)
            startActivity(intent)
        }
    }
}