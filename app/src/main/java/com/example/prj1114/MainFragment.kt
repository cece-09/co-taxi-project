package com.example.prj1114

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prj1114.databinding.MainFragmentBinding
import com.example.prj1114.util.NodeServer
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MainFragment : Fragment() {
    private var mbinding: MainFragmentBinding? = null
    private val binding get() = mbinding!!
    private val nodeServer: NodeServer.NodeServer = NodeServer.NodeServer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button1.text = "로그인/회원가입"
        binding.button2.text = "검색 또는 생성"
        binding.button3.text = "상세화면 조회 및 채팅"
        binding.button4.text = "현재 기기로 푸시 알림"
        binding.button4.isEnabled = false

        binding.button1.setOnClickListener {
            val intent = Intent(activity, Act01Regis::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(activity, Act02Search::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(activity, Act06Detail::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            var myToken: String? = null
            Firebase.messaging.token.addOnCompleteListener {task ->
                if(!task.isSuccessful) {
                    return@addOnCompleteListener
                }
                myToken = task.result
                nodeServer.fcm(arrayOf(myToken!!))
            }
        }

        return view
    }

    fun callFragment(destination:Int){
        (activity as MainActivity).replaceFragment(destination)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null
    }
}
