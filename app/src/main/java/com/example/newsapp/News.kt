package com.example.newsapp

import cn.bmob.v3.BmobObject

data class News(val title: String?="",
                val image: String?="",
                val content: String?="",
                val newsAgency: String?="",
                val newsDate: String?="",
                val pageView: String?=""): BmobObject()
