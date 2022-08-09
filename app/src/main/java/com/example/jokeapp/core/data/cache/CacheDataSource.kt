package com.example.jokeapp.core.data.cache

import com.example.jokeapp.core.data.ChangeStatus
import com.example.jokeapp.core.data.DataFetcher
import com.example.jokeapp.data.CommonDataModel

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {
    suspend fun getDataList() : List<CommonDataModel<E>>
    suspend fun remove(id: E)
}