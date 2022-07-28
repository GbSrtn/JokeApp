package com.example.jokeapp.core.domain

import com.example.jokeapp.domain.CommonItem

interface CommonInteractor {
    suspend fun getItem() : CommonItem
    suspend fun  changeFavourites() : CommonItem
    fun  getFavourites(favorites: Boolean)
}