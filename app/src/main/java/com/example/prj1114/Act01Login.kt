package com.example.prj1114

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.prj1114.databinding.ActivityAct01LoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Act01Login : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth // FirebaseAuth 의 인스턴스를 선언.
    private lateinit var binding : ActivityAct01LoginBinding // 바인딩 클래스 선언.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth // onCreate() 메서드에서 FirebaseAuth 인스턴스를 초기화.

        binding = DataBindingUtil.setContentView(this, R.layout.activity_act01_login)

        binding.loginLoginButton.setOnClickListener {

            val email = binding.loginEmailEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, Act04List::class.java) // 로그인 성공시 검색 화면으로 이동.
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
              }
            }
        }
    }
}