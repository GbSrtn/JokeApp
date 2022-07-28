package com.example.jokeapp.domain

import com.example.jokeapp.core.data.CommonDataModelMapper
import com.example.jokeapp.core.data.CommonRepository
import com.example.jokeapp.core.domain.CommonInteractor
import com.example.jokeapp.core.domain.FailureHandler
import java.lang.Exception

class BaseInteractor<E>(
    private val repository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success, E>
) : CommonInteractor {
    override suspend fun getItem(): CommonItem {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): CommonItem {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override fun getFavourites(favorites: Boolean) =
        repository.chooseDataSource(favorites)
}