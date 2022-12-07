package com.example.prj1114.viewmodel

import android.media.session.MediaSession.Token
import androidx.lifecycle.ViewModel
import com.example.prj1114.data.BaseRepository
import com.example.prj1114.data.User
import com.example.prj1114.util.NaverLogin
import com.example.prj1114.util.NodeServer.NodeServer

class RegisViewModel : ViewModel() {
    private val tag = "APPLE/ ${this::class.java.simpleName}"

    /** TODO: liveData 로 만들 것 */
    /** data to send when register command triggered */
    var userId: String? = null
    var userGender: Int? = null
    var userNickname: String? = null

    private val dataRepo: BaseRepository = BaseRepository()
    private val nodeServer: NodeServer = NodeServer()
    private val naverRepo: NaverLogin = NaverLogin()

    suspend fun sendEmail(email: String): String {
        return nodeServer.sendEmail(email)
    }

    fun fcm(tokens: Array<String>) {
        return nodeServer.fcm(tokens)
    }

    suspend fun saveUser(user: User) {
        return dataRepo.create(user.asDto())
    }
}