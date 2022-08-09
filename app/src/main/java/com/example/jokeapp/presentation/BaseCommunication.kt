package com.example.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.core.presentation.CommonCommunication
import com.example.jokeapp.core.presentation.Communication

class BaseCommunication<T>: CommonCommunication<T> {
    private val liveData = MutableLiveData<State>()

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    private val listLiveData = MutableLiveData<ArrayList<CommonUiModel<T>>>()

    override fun showDataList(list: List<CommonUiModel<T>>) {
        listLiveData.value = ArrayList(list)
    }

    override fun observerList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>) {
        listLiveData.observe(owner, observer)
    }

    override fun getList(): List<CommonUiModel<T>> {
        return listLiveData.value ?: emptyList()
    }

    override fun removeItem(id: T): Int {
        val found = listLiveData.value?.find {
            it.matches(id)
        }
        val position = listLiveData.value?.indexOf(found) ?: -1
        found?.let {
            listLiveData.value?.remove(it)
        }
        return position
    }
}