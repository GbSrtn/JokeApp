package com.example.jokeapp.core.data.cache

import com.example.jokeapp.core.data.ChangeStatus
import com.example.jokeapp.core.data.DataFetcher

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {

}