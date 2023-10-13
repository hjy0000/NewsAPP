package com.example.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import kotlinx.coroutines.launch


class NewsAppViewModel: ViewModel() {

    private val _newsDataState = MutableLiveData<NewsDataState<List<News>?>>()
    val newsDataState = _newsDataState

    fun loadData(){
        _newsDataState.value = NewsDataState.Loading()
        viewModelScope.launch {
            val query: BmobQuery<News> = BmobQuery<News>()
            query.findObjects(object : FindListener<News>() {
                    override fun done(newsdata: List<News>?, e: BmobException?) {
                        if (e == null) {
                            _newsDataState.value = NewsDataState.Success(newsdata)
                        } else {
                            _newsDataState.value = NewsDataState.Error("出错了")
                        }
                    }
                })
        }
    }

}