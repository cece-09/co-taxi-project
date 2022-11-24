package com.example.prj1114

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

class AddressActivity : AppCompatActivity() {
    companion object {
        const val ADDRESS_REQUEST_CODE = 2928
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val webView = findViewById<WebView>(R.id.web_view)

        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(KaKaoJavaScriptInterface(), "Android")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                webView.loadUrl("javascript:execKakaoPostcode();")
            }
        }

        webView.loadUrl("http://teamcsw4010.blogspot.com/2022/11/welcome-to-firebase-hosting-load-core_24.html")
    }

    inner class KaKaoJavaScriptInterface {

        @JavascriptInterface
        fun processDATA(address: String?) {
            Intent().apply {
                putExtra("address", address)
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }
}