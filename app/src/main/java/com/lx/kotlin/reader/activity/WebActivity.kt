package com.lx.kotlin.reader.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lx.kotlin.reader.R
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        toolbar.title = intent.getStringExtra("title")
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_36dp)
        toolbar.setNavigationOnClickListener { finish() }
        swipeRefresh.setColorSchemeResources(R.color.colorRefresh1,
                R.color.colorRefresh2, R.color.colorRefresh3,
                R.color.colorRefresh4, R.color.colorRefresh5)
        webView.setWebViewClient(CustomWebViewClient())
        webView.loadUrl(intent.getStringExtra("url"))
        webView.settings.javaScriptEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.stopLoading()
        webView.destroy()
    }

    private inner class CustomWebViewClient:WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            swipeRefresh.isRefreshing = false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            swipeRefresh.isRefreshing = true
        }


    }
}
