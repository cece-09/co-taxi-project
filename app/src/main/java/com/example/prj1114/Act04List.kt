package com.example.prj1114

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_act04_list.*

class Act04List : Activity(){


    private lateinit var auth : FirebaseAuth
    val db = FirebaseFirestore.getInstance()  // firestore 객체 얻기.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act04_list)

        val db = Firebase.firestore // Cloud Firestore의 인스턴스를 초기화.

        auth = FirebaseAuth.getInstance()

        // 현재 사용자의 uid를 넘겨준다.
        goToCreateButton.setOnClickListener {
            val intent = Intent(this, Act05Create::class.java)
            intent.putExtra("uid", auth.currentUser?.uid)
            startActivity(intent)
        }
    }
}