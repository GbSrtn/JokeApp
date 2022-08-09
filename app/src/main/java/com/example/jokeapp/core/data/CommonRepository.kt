package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface CommonRepository<E> {
    suspend fun changeStatus(): CommonDataModel<E>
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun getCommonItemList(): List<CommonDataModel<E>>
    fun chooseDataSource(cached : Boolean)
    suspend fun removeItem(id: E)
}