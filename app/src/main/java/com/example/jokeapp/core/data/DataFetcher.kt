package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface DataFetcher {
    suspend fun getData(): CommonDataModel
}