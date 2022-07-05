package com.example.jokeapp

import android.util.Log
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource{

    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        return try {
            val result : JokeServerModel = service.getJoke().execute().body()!!
            Log.d("ThreadLogTag", "currentThread : ${Thread.currentThread().name}")
            Result.Success(result)
        } catch (e: Exception) {
            val errorType = if (e is UnknownHostException) {
                ErrorType.NO_CONNECTION
            } else {
                ErrorType.SERVICE_UNAVALIABLE
            }
            Result.Error(errorType)
        }
    }
}