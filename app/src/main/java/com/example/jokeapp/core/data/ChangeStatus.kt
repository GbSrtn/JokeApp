package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface ChangeStatus<E> {
    suspend fun addOrRemove(id: E, model: CommonDataModel<E>) : CommonDataModel<E>
}