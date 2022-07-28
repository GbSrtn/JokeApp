package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface CommonRepository {
    suspend fun changeStatus(): CommonDataModel
    suspend fun getCommonItem(): CommonDataModel
    fun chooseDataSource(cached : Boolean)
}