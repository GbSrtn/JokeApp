package com.example.jokeapp.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import com.example.jokeapp.core.presentation.customViews.EnableView
import com.example.jokeapp.core.presentation.customViews.ShowImage
import com.example.jokeapp.core.presentation.customViews.ShowText
import com.example.jokeapp.core.presentation.customViews.ShowView
import com.example.jokeapp.core.domain.CommonInteractor
import com.example.jokeapp.core.presentation.Communication
import com.example.jokeapp.core.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel(
    private val interactor: CommonInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel {

    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL))
                interactor.changeFavourites().to().show(communication)
        }
    }

    override fun chooseFavourites(favourites: Boolean) = interactor.getFavourites(favourites)

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) = communication.observe(owner, observer)

}




