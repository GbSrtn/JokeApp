package com.example.jokeapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.jokeapp.presentation.State

interface CommonViewModel {
    fun getItem()
    fun changeItemStatus()
    fun chooseFavourites(favourites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}