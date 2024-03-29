package com.example.jokeapp.data

import com.example.jokeapp.core.data.ChangeCommonItem
import com.example.jokeapp.core.data.CommonDataModelMapper
import com.example.jokeapp.core.data.ChangeStatus
import com.example.jokeapp.core.presentation.customViews.ShowText
import com.example.jokeapp.presentation.customViews.CorrectTextView

class CommonDataModel<E>(
    private val id: E,
    private val firstText: String,
    private val secondText: String,
    private val cached: Boolean = false
) : ChangeCommonItem<E> {

    fun <T> map(mapper: CommonDataModelMapper<T, E>): T {
        return mapper.map(id, firstText, secondText, cached)
    }

    override suspend fun change(changeStatus: ChangeStatus<E>) =
        changeStatus.addOrRemove(id,this)

    fun changeCached(cached: Boolean) = CommonDataModel(id,firstText,secondText,cached)
}
