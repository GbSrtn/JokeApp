package com.example.jokeapp.domain

import java.io.IOException

class Exceptions {
}

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedDataException() : IOException()