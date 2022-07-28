package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.core.data.cache.CachedData
import com.example.jokeapp.core.data.ChangeStatus
import com.example.jokeapp.data.CommonDataModel

class BaseCachedData<E>: CachedData<E> {
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()

    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}