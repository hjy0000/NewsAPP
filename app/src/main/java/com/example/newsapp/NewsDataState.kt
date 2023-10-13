package com.example.newsapp

import androidx.lifecycle.LiveData

sealed class NewsDataState<T> {
    class  Loading<T> : NewsDataState<T>()
    data class Success<T>(val data:T) : NewsDataState<T>()
    data class Error<T>(val errorMessage:String): NewsDataState<T>()
}