package com.example.jokeapp

import com.example.jokeapp.domain.NoCachedJokesException
import com.example.jokeapp.domain.NoConnectionException
import com.example.jokeapp.domain.ServiceUnavailableException

class JokeFailureFactory(private val resourseManager: ResourseManager) : JokeFailureHandler {
    override fun handle(e: Exception): JokeFailure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourseManager)
            is NoCachedJokesException -> NoCachedJokes(resourseManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourseManager)
            else -> GenericError(resourseManager)
        }
    }
}