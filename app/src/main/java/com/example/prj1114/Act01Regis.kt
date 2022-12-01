package com.example.prj1114

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.prj1114.databinding.ActivityAct01RegisBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Act01Regis : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityAct01RegisBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act01_regis)

        auth = Firebase.auth


        binding = DataBindingUtil.setContentView(this, R.layout.activity_act01_regis)

        binding.introLoginButton.setOnClickListener {
            val intent = Intent(this, Act01Login::class.java)
            startActivity(intent)
        }

        binding.introJoinButton.setOnClickListener {
            val intent = Intent(this, Act01Join::class.java)
            startActivity(intent)
        }
    }
}