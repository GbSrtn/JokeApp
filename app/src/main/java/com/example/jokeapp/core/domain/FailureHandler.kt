package com.example.jokeapp.core.domain

import com.example.jokeapp.core.presentation.Failure

interface FailureHandler {
    fun handle(e: Exception) : Failure
}