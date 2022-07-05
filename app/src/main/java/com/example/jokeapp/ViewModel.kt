package com.example.jokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel(){

    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        dataCallback = callback
    }

    fun changeJokeStatus() = viewModelScope.launch {
        val uiModel = model.changeJokeStatus()
        dataCallback?.let {
            uiModel?.map(it)
        }
    }

    fun chooseDataSource(favourites: Boolean) {
        model.chooseDataSource(favourites)
    }

    fun getJoke() = viewModelScope.launch {
        val uiModel = model.getJoke()
        dataCallback?.let {
            uiModel.map(it)
        }

    }


    fun clear() {
        dataCallback = null
    }
}



