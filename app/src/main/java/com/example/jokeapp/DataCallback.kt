package com.example.jokeapp

import androidx.annotation.DrawableRes

interface DataCallback {
    fun provideText(text: String)

    fun provideIconResId(@DrawableRes id: Int)
}