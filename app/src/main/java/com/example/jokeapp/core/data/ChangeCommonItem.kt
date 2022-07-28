package com.example.jokeapp.core.data

import com.example.jokeapp.data.CommonDataModel

interface ChangeCommonItem<E> {
    suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>

    class Empty<E> : ChangeCommonItem<E> {
        override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
            throw IllegalStateException("empty change item status")
        }
    }
}