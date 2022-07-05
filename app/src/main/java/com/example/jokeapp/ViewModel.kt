package com.example.jokeapp

class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null

    private var jokeCallback = object : JokeCallback {
        override fun provide(joke: JokeUiModel) {
            dataCallback?.let {
                joke.map(it)
            }
        }

    }

    fun init(callback: DataCallback) {
        dataCallback = callback
        model.init(jokeCallback)
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

    fun chooseDataSource(favourites: Boolean) {
        model.chooseDataSource(favourites)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}



