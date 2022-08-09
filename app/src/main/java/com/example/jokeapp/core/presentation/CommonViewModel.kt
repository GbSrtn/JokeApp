package com.example.jokeapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.presentation.State

interface CommonViewModel<T> : CommonItemViewModel {
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)
    fun changeItemStatus(id: T) : Int
}

interface CommonItemViewModel {
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun chooseFavourites(favourites: Boolean)

}