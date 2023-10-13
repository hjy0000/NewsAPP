package com.example.newsapp

import android.app.Application
import cn.bmob.v3.Bmob

class NewsAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //928a5255491d81fd36035aa97ffa2ecd
        Bmob.initialize(this, "928a5255491d81fd36035aa97ffa2ecd")
    }

}