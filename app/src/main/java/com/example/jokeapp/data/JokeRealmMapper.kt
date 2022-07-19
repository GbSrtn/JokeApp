package com.example.jokeapp.data

class JokeRealmMapper : JokeDataModelMapper<JokeRealm> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean): JokeRealm {
        return JokeRealm().also { joke ->
            joke.id = id
            joke.text = text
            joke.punchline = punchline
        }
    }
}