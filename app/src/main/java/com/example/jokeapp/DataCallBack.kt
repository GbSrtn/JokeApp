package com.example.jokeapp

import androidx.annotation.DrawableRes

interface DataCallBack {
    fun provideText(text: String)

    fun provideIconResId(@DrawableRes id: Int)
}