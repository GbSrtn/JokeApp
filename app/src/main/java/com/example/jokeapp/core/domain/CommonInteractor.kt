package com.example.jokeapp.core.domain

import com.example.jokeapp.domain.CommonItem

interface CommonInteractor<T> {
    suspend fun getItem() : CommonItem<T>
    suspend fun getItemList() : List<CommonItem<T>>
    suspend fun  changeFavourites() : CommonItem<T>
    fun  getFavourites(favorites: Boolean)
    suspend fun removeItem(id: T)
}