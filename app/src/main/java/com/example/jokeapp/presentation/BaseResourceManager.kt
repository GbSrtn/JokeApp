package com.example.jokeapp.presentation

import android.content.Context
import com.example.jokeapp.ResourseManager

class BaseResourseManager(private val context: Context) : ResourseManager {
    override fun getString(stringResId: Int) = context.getString(stringResId)
}