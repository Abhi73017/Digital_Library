package com.abhishek.digital_library

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_pdf_reader.*

class pdfReader : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_reader)

        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.visibility = View.VISIBLE
                loader_progress.visibility = View.INVISIBLE
            }

        }
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true

        val pdflink = intent.getStringExtra("url") ?: return

        webView.loadUrl(pdflink)
    }
}
