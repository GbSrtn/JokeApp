package com.example.jokeapp

import android.content.Context
import androidx.annotation.StringRes

interface ResourseManager {
    fun getString(@StringRes stringResId: Int) : String
}
