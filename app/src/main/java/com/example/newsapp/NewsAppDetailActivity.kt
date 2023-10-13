package com.example.newsapp

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityNewsappDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsAppDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityNewsappDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsappDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            webViewNews.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.Main) {
                // 这里是在延迟一秒后执行的代码
                delay(1000) // 延迟一秒
                webViewNews.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
        // 获取从上一个 Activity 传递过来的 URL
        val url = intent.getStringExtra("newsUrl")

        // 配置 WebView
        binding.webViewNews.apply {
            val webSettings: WebSettings = settings
            webSettings.javaScriptEnabled = true // 启用 JavaScript
            // 设置 WebViewClient，以便在 WebView 中打开链接，而不是跳转到系统浏览器
            webViewClient = WebViewClient()
            // 加载 URL
            loadUrl(url!!)
        }
        binding.tvBack.setOnClickListener { finish() }
    }
}