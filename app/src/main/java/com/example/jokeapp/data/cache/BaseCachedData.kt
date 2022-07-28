package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.core.data.cache.CachedData
import com.example.jokeapp.core.data.ChangeStatus
import com.example.jokeapp.data.CommonDataModel

class BaseCachedData: CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()

    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}