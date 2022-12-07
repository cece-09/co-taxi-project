package com.example.prj1114.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prj1114.common.CurrentInfo
import com.example.prj1114.data.Chat
import com.example.prj1114.data.ChatDto
import com.example.prj1114.data.ChatRepository
import com.example.prj1114.util.NaverLogin
import com.google.firebase.Timestamp
import kotlinx.coroutines.*

class ChatViewModel: ViewModel() {
    /** dependencies */
    /** TODO : 공통 부분은 BaseViewModel 을 만들어 상속으로 묶을 것 */
    private val naver = NaverLogin()
    private val job = viewModelScope
    private val repository = ChatRepository()

    lateinit var currentUser: String
    lateinit var currentTeam: String
    init {
        job.launch { currentUser = naver.getCurrentUser() }
    }

    /** liveData */
    private var _input = MutableLiveData<String>()
    val input: LiveData<String> get() = _input

    fun setInput(text: String) {
        _input.value = text
    }

    /** save to repository */
    fun sendChat() {
        job.launch {
            input.value?.let {
                repository.create(
                    Chat(CurrentInfo.userId,CurrentInfo.teamId, it, Timestamp.now())
                        .asDto()
                )

            }
        }

    }

    /** get Chat data from db */
    suspend fun getChat(): ArrayList<Chat> {
        return repository.get(Pair("teamId", CurrentInfo.teamId))
    }

}