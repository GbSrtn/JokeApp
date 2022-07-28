package com.example.jokeapp.core.data.cache

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.data.CommonDataModel

interface CachedData : ChangeCommonItem {
    fun save(data: CommonDataModel)
    fun clear()
}