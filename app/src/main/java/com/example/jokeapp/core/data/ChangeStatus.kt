package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface ChangeStatus {
    suspend fun addOrRemove(id: Int, model: CommonDataModel) : CommonDataModel
}