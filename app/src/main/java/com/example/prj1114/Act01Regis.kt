package com.example.prj1114

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.prj1114.data.User
import com.example.prj1114.databinding.Act1RegisBinding
import com.example.prj1114.util.NaverLogin
import com.example.prj1114.viewmodel.RegisViewModel
import com.navercorp.nid.NaverIdLoginSDK
import kotlinx.coroutines.*

class Act01Regis : AppCompatActivity(){

    object TempUserInfo {
        lateinit var userId: String
        lateinit var gender: String
        lateinit var nickname: String
        lateinit var email: String
        lateinit var currentDeviceToken: String
        lateinit var naverAccessToken: String
    }

    private lateinit var binding: Act1RegisBinding
    private lateinit var viewModel: RegisViewModel
    private lateinit var naver: NaverLogin
    private lateinit var context: Context
    private lateinit var code: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Act1RegisBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[RegisViewModel::class.java]
        naver = NaverLogin()
        setContentView(binding.root)
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        naver.logout()
    }

    private fun initialize() {
        context = this
        val clientId = "gr3SF8Z7Xn4PJYc5pFUp"
        val clientSecret = "w8vrhlLfAl"
        val clientName = "prj1114"

        NaverIdLoginSDK.apply {
            showDevelopersLog(true)
            initialize(context, clientId, clientSecret, clientName)
            isShowMarketLink = true
            isShowBottomTab = true
        }

        /** naver login */
        binding.buttonOAuthLoginImg.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                TempUserInfo.naverAccessToken = naver.login(this@Act01Regis)
                TempUserInfo.userId = naver.getProfile().userId
                TempUserInfo.gender = naver.getProfile().gender
                nextFlipper()
            }
        }

        /** send verification email */
        binding.btnSendEmail.setOnClickListener {
            val email = binding.textEmail.text?.toString()
            when(email.isNullOrBlank()) {
                true -> warning(binding.warningEmail, "please enter valid email")
                false -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        code = viewModel.sendEmail(email)
                    }
                    TempUserInfo.email = email
                    showCheckCode()
                    warning(binding.warningEmail, "이메일을 보냈습니다.")
                }
            }
        }

        /** verify user code */
        binding.btnCheckCode.setOnClickListener {
            when(binding.textCode.text?.toString().equals(code)) {
                true -> {
                    binding.viewFlipper.showNext()
                }
                false -> warning(binding.warningCode, "please enter valid code")
            }
        }

        /** create user account and login */
        binding.btnRegister.setOnClickListener {
            val nickname = binding.textNickname.text.toString()
            when(nickname.isBlank()) {
                true -> warning(binding.warningNickname, "please enter valid nickname")
                false -> {
                    TempUserInfo.nickname = nickname
                    CoroutineScope(Dispatchers.IO).launch {
                        TempUserInfo.let {
                            viewModel.saveUser(User(it.userId, it.nickname, it.gender))
                        }
                        // Toast.makeText(context, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        // navigateToMain()
                    }
                }
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun warning(textView: TextView, warning: String) {
        textView.text = warning
    }

    /** go to home activity */
//    private fun toNextActivity(userId: String) {
//        val next = Act02Search::class.java
//        val intent = Intent(this@Act01Regis,next).apply {
//            putExtra("userId", userId)
//        }
//        startActivity(intent)
//    }

    private fun nextFlipper() {
        binding.viewFlipper.showNext()
    }
    private fun showCheckCode() {
        binding.textCode.isVisible = true
        binding.btnCheckCode.isVisible = true
    }
}