package com.example.jokeapp.Test

import com.example.jokeapp.*

class TestModel(resourseManager: ResourseManager) : Model {

    private var callback: JokeCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourseManager)
    private val serviceUnavailable = ServiceUnavailable(resourseManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provide(BaseJoke("BaseText", "BasePunchline"))
                1 -> callback?.provide(FavouriteJoke("FavouriteText", "BasePunchline"))
                2 -> callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: JokeCallback) {
        this.callback = callback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        TODO("Not yet implemented")
    }

    override fun chooseDataSource(cached: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        callback = null
    }
}