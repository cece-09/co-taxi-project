package com.example.prj1114

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.prj1114.databinding.ActivityAct01JoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Act01Join : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth // FirebaseAuth 의 인스턴스를 선언.

    private lateinit var binding : ActivityAct01JoinBinding// 바인딩 클래스 선언.



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth // onCreate() 메서드에서 FirebaseAuth 인스턴스를 초기화.

        binding = DataBindingUtil.setContentView(this, R.layout.activity_act01_join)

        binding.joinJoinButton.setOnClickListener {

            var verifyJoin = true // 회원가입에 필요한 입력값들에 대한 제약 조건들이 충족되었는지 확인하기 위한 용도.

            val email = binding.joinEmailEditText.text.toString()
            val password1 = binding.joinPasswordEditText.text.toString()
            val password2 = binding.joinPasswordConfirmEditText.text.toString()

            // 이메일 입력란이 공백인 경우
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                verifyJoin = false
            }
            // 비밀번호 입력란이 공백인 경우
            if(password1.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                verifyJoin = false
            }
            // 비밀번호 확인 입력란이 공백인 경우
            if(password2.isEmpty()) {
                Toast.makeText(this, "동일한 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                verifyJoin = false
            }

            // 두 비밀번호가 동일한지 확인
            if(!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show()
                verifyJoin = false
            }

            // 비밀번호가 8자리 이상이도록
            if (password1.length < 8) {
                Toast.makeText(this, "비밀번호는 8자리 이상으로 설정해주세요", Toast.LENGTH_LONG).show()
                verifyJoin = false
            }

            // 위 5가지 조건들이 모두 충족된 경우에 해당.
            // 이메일 주소와 비밀번호를 가져와 유효성을 검사한 다음 createUserWithEmailAndPassword 메소드를 사용하여 새 사용자를 생성하는 새로운 createAccount 메소드를 생성.
            if(verifyJoin) {
                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show() // 회원가입이 정상적으로 완료되었으면 "성공"이라는 Toast 메세지를 출력하고

                        val intent = Intent(this, Act01Login::class.java) // 로그인 화면으로 바로 이동된다.
                        startActivity(intent)

                    } else { // 회원가입에 실패한 경우.
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}