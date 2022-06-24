package com.example.jokeapp

class TestModel(resourseManager: ResourseManager) : Model {

    private var callback: ResultCallBack? = null
    private var count = 0
    private val noConnection = NoConnection(resourseManager)
    private val serviceUnavailable = ServiceUnavailable(resourseManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("testText", "testPunchline"))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailable)
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: ResultCallBack) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}