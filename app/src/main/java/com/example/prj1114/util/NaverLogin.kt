package com.example.prj1114.util

import android.content.Context
import android.util.Log
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthBehavior
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class NaverLogin {
    companion object {
        private var TAG = "APPLE/ naver sdk"
    }

    /** data object */
    data class NaverProfile(
        val userId: String,
        val gender: String
    )

    /** naver login */
    suspend fun login(context: Context): String {
        NaverIdLoginSDK.behavior = NidOAuthBehavior.CUSTOMTABS
        return suspendCoroutine { continuation ->
            NaverIdLoginSDK.authenticate(context, object: OAuthLoginCallback {
                override fun onSuccess() {
                    Log.d(TAG, "Naver Token: ${NaverIdLoginSDK.getAccessToken()}")
                    continuation.resume(NaverIdLoginSDK.getAccessToken().toString())
                }
                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    val error = Throwable("error code: $errorCode message: $message")
                    Log.w(TAG, "error code: $errorCode, $errorDescription")
                    continuation.resumeWithException(error)
                }
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            })
        }
    }

    /** get user profile from naver login */
    suspend fun getProfile(): NaverProfile {
        return suspendCoroutine { continuation ->
            NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                override fun onSuccess(result: NidProfileResponse) {
                    Log.d(TAG, "user info : ${result.profile}")
                    result.profile?.let {
                        continuation.resume(NaverProfile(it.id.toString(), it.gender.toString()))
                    }
                }
                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    val error = Throwable("error code: $errorCode message: $message")
                    Log.w(TAG, "error code: $errorCode, $errorDescription")
                    continuation.resumeWithException(error)
                }
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            })
        }
    }

    suspend fun getCurrentUser(): String {
        return getProfile().userId
    }

    fun logout() {
        return NaverIdLoginSDK.logout()
    }
}