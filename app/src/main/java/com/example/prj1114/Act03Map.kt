package com.example.prj1114

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class Act03Map : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act3_map)

        var btn:Button
        var ttx:EditText
        btn=findViewById(R.id.btnMap01)
        ttx=findViewById(R.id.editTextTextPostalAddress)

        btn.setOnClickListener {
            setResult(ttx.text.hashCode().toInt())
            finish()
        }
    }
}
/* // 네이버 검색 API 예제 - 블로그 검색
object ApiExamSearchBlog {
    @JvmStatic
    fun main(args: Array<String>) {
        val clientId = "YOUR_CLIENT_ID" //애플리케이션 클라이언트 아이디
        val clientSecret = "YOUR_CLIENT_SECRET" //애플리케이션 클라이언트 시크릿
        var text: String? = null
        text = try {
            URLEncoder.encode("그린팩토리", "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("검색어 인코딩 실패", e)
        }
        val apiURL =
            "https://openapi.naver.com/v1/search/blog?query=$text" // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과
        val requestHeaders: MutableMap<String, String> = HashMap()
        requestHeaders["X-Naver-Client-Id"] = clientId
        requestHeaders["X-Naver-Client-Secret"] = clientSecret
        val responseBody = ApiExamSearchBlog[apiURL, requestHeaders]
        println(responseBody)
    }

    private operator fun get(apiUrl: String, requestHeaders: Map<String, String>): String {
        val con = connect(apiUrl)
        return try {
            con.requestMethod = "GET"
            for ((key, value): Map.Entry<String, String> in requestHeaders) {
                con.setRequestProperty(key, value)
            }
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                readBody(con.inputStream)
            } else { // 오류 발생
                readBody(con.errorStream)
            }
        } catch (e: IOException) {
            throw RuntimeException("API 요청과 응답 실패", e)
        } finally {
            con.disconnect()
        }
    }

    private fun connect(apiUrl: String): HttpURLConnection {
        return try {
            val url = URL(apiUrl)
            url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw RuntimeException("API URL이 잘못되었습니다. : $apiUrl", e)
        } catch (e: IOException) {
            throw RuntimeException("연결이 실패했습니다. : $apiUrl", e)
        }
    }

    private fun readBody(body: InputStream): String {
        val streamReader = InputStreamReader(body)
        try {
            BufferedReader(streamReader).use { lineReader ->
                val responseBody = StringBuilder()
                var line: String?
                while (lineReader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }
                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("API 응답을 읽는 데 실패했습니다.", e)
        }
    }
}*/