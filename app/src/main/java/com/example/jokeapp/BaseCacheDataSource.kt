package com.example.jokeapp

import android.util.Log
import io.realm.Realm
import io.realm.kotlin.isValid

class BaseCacheDataSource(private val realm: Realm) : CacheDataSource {
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty()) {
                jokeCachedCallback.fail()
            } else {
                jokes.random().let { joke ->
                    jokeCachedCallback.provide(
                        JokeServerModel(
                            joke.id,
                            joke.text,
                            joke.puchline
                        )
                    )

                }
            }
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        realm.let {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
            return if(jokeRealm == null) {
                val newJoke = jokeServerModel.toJokeRealm()
                it.executeTransactionAsync { transaction ->
                    transaction.insert(newJoke)
                }
                jokeServerModel.toFavouriteJoke()
            } else {

                Log.d("TAGG", "addOrRemove: ${jokeRealm.id} ")
                Log.d("TAGG", "addOrRemove: ${jokeRealm.text} ")
                Log.d("TAGG", "addOrRemove: ${jokeRealm.puchline} ")

                it.executeTransactionAsync {
                    // при удалении шутки из реалма происходит ошибка:
                    // Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.
                    // не знаю, как исправить, но буду двигаться далььше
                    jokeRealm.deleteFromRealm()
                }

                Log.d("TAGG", "addOrRemove:  BIG JOPA")

                jokeServerModel.toBaseJoke()
            }
        }
    }
}