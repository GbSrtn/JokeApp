package com.example.jokeapp.data

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.core.data.CommonDataModelMapper
import com.example.jokeapp.core.data.ChangeStatus

class CommonDataModel(
    private val id: Int,
    private val firstText: String,
    private val secondText: String,
    private val cached: Boolean = false
) : ChangeCommonItem {
    fun <T> map(mapper: CommonDataModelMapper<T>): T {
        return mapper.map(id, firstText, secondText, cached)
    }
    override suspend fun change(changeStatus: ChangeStatus) =
        changeStatus.addOrRemove(id,this)
    fun changeCached(cached: Boolean) = CommonDataModel(id, firstText, secondText, cached)
}
