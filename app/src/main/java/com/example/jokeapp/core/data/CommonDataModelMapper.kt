package com.example.jokeapp.core.data

interface CommonDataModelMapper<T, E> {
    fun map(id: E, firstText: String, secondText: String, cached: Boolean): T
}

