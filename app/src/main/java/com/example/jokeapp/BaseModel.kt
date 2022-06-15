package com.example.jokeapp

class BaseModel(
    private val service: JokeService,
    private val resourseManager: ResourseManager
) : Model {
    private var callback: ResultCallBack? = null
    private val noConnection by lazy { NoConnection(resourseManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourseManager) }

    override fun getJoke() {
        service.getJoke(object : ServiceCallback {
            override fun returnSuccess(data: String) {
                callback?.provideSuccess(Joke(data, ""))
            }

            override fun returnError(type: ErrorType) {
                when (type) {
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                    ErrorType.OTHER -> callback?.provideError(serviceUnavailable)
                }
            }

        })
    }

    override fun init(callback: ResultCallBack) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}