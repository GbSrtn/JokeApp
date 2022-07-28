package com.example.jokeapp.core.data.cache

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.data.CommonDataModel

interface CachedData<E> : ChangeCommonItem<E> {
    fun save(data: CommonDataModel<E>)
    fun clear()
}