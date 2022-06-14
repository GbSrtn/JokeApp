package com.example.jokeapp

import android.content.Context
import androidx.annotation.StringRes

interface ResourseManager {
    fun getString(@StringRes stringResId: Int) : String
}

class BaseResourseManager(private val context: Context) : ResourseManager {
    override fun getString(stringResId: Int) = context.getString(stringResId)
}