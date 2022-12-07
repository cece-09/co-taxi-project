package com.example.prj1114.util

import android.util.Log
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NodeServer {
    data class ResponseDC(var result: String? = null)
    data class ResponseMA(
        var status: String,
        var info: String,
        var number: Number
    )

    interface RetrofitInterface {
        @GET("/")
        fun getRequest(@Query("name") name: String): Call<ResponseDC>

        @FormUrlEncoded
        @POST("/fcm")
        fun fcmPostRequest(
            @Field("title") title: String,
            @Field("body") body: String,
            @Field("tokens") tokens: Array<String>): Call<ResponseDC>

        @FormUrlEncoded
        @POST("/mail")
        fun mailPostRequest(@Field("email") email: String): Call<ResponseMA>

        @FormUrlEncoded
        @PUT("/{id}")
        fun putRequest(@Path("id")id: String,
                       @Field("content")content: String): Call<ResponseDC>

        @DELETE("/{id}")
        fun deleteRequest(@Path("id")id: String): Call<ResponseDC>
    }

    class NodeServer {
        private val url = "http://3.38.24.214:3000"
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val server = retrofit.create(RetrofitInterface::class.java)

        suspend fun sendEmail(email: String): String {
            val result: ResponseMA = server.mailPostRequest(email).await()
            return result.number.toString()
        }
        fun fcm(tokens: Array<String>) {
            server.fcmPostRequest("sample", "푸시 알림 테스트", tokens)
                .enqueue(object : Callback<ResponseDC>{
                    override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                        Log.w("APPLE/ FCM", "request fail", t)
                    }
                    override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                        Log.d("APPLE/ FCM", "request success $response")
                    }
                })

        }
    }
}