package com.example.jokeapp.data

import com.example.jokeapp.*
import com.example.jokeapp.core.data.CommonRepository
import com.example.jokeapp.core.data.DataFetcher
import com.example.jokeapp.core.data.cache.CacheDataSource
import com.example.jokeapp.core.data.cache.CachedData
import com.example.jokeapp.core.data.net.CloudDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedData
) : CommonRepository {
    private var currentDataSource : DataFetcher = cloudDataSource

    override suspend fun changeStatus(): CommonDataModel = cachedJoke.change(cacheDataSource)

    override suspend fun getCommonItem(): CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cachedJoke.save(data)
            return@withContext data
        } catch (e: Exception) {
            cachedJoke.clear()
            throw e
        }
    }

    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }
}
