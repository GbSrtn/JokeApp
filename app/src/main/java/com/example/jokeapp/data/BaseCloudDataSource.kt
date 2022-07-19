package com.example.jokeapp.data

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.domain.NoConnectionException
import com.example.jokeapp.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<JokeDataModel>> : CloudDataSource {
    protected abstract fun getJokeServerModel() : Call<T>

    override suspend fun getJoke(): JokeDataModel {
        try {
            return getJokeServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}

//class BaseCloudDataSource(private val service: JokeService<*>) : CloudDataSource {
//    override suspend fun getJoke():  JokeDataModel {
//        try {
//            return  service.getJoke().execute().body()!!.to()
//        } catch (e: Exception) {
//            if (e is UnknownHostException) {
//                throw NoConnectionException()
//            } else {
//                throw ServiceUnavailableException()
//            }
//        }
//    }
//}