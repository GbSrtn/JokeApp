package com.example.jokeapp.domain

import com.example.jokeapp.*
import com.example.jokeapp.core.domain.FailureHandler
import com.example.jokeapp.core.presentation.Failure
import com.example.jokeapp.presentation.GenericError
import com.example.jokeapp.presentation.NoCachedData
import com.example.jokeapp.presentation.NoConnection
import com.example.jokeapp.presentation.ServiceUnavailable

class FailureFactory(private val resourseManager: ResourseManager) : FailureHandler {
    override fun handle(e: Exception): Failure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourseManager)
            is NoCachedDataException -> NoCachedData(resourseManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourseManager)
            else -> GenericError(resourseManager)
        }
    }
}