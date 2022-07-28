package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface ChangeCommonItem {
    suspend fun change(changeStatus: ChangeStatus): CommonDataModel

    class Empty : ChangeCommonItem {
        override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change item status")
        }
    }
}