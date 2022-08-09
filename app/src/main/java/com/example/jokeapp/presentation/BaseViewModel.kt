package com.example.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.core.domain.CommonInteractor
import com.example.jokeapp.core.presentation.CommonCommunication
import com.example.jokeapp.core.presentation.CommonViewModel
import com.example.jokeapp.domain.CommonItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel<T>(
    private val interactor: CommonInteractor<T>,
    private val communication: CommonCommunication<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel<T> {

    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    private fun <T>List<CommonItem<T>>.toUiList() = map { it.to() }

    override fun getItemList() {
        viewModelScope.launch(dispatcher) {
            communication.showDataList(interactor.getItemList().toUiList())
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                interactor.changeFavourites().to().show(communication)
                communication.showDataList(interactor.getItemList().toUiList())
            }
        }

    }

    override fun chooseFavourites(favourites: Boolean) = interactor.getFavourites(favourites)

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>    >>) =
        communication.observerList(owner, observer)


    override fun changeItemStatus(id: T): Int {
        val position = communication.removeItem(id)
        viewModelScope.launch(dispatcher) {
            interactor.removeItem(id)
        }
        return position
    }

}




