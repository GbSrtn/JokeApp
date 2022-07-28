package com.example.jokeapp.data.net

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.core.data.net.CloudDataSource
import com.example.jokeapp.data.CommonDataModel
import com.example.jokeapp.domain.NoConnectionException
import com.example.jokeapp.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T: Mapper<CommonDataModel>> : CloudDataSource {

    protected abstract fun getServerModel() : Call<T>

    override suspend fun getData(): CommonDataModel {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}
