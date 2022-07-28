package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface DataFetcher<E> {
    suspend fun getData(): CommonDataModel<E>
}