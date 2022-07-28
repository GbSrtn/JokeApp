package com.example.jokeapp.core.data

interface CommonDataModelMapper<T> {
    fun map(id: Int, firstText: String, secondText: String, cached: Boolean): T
}

