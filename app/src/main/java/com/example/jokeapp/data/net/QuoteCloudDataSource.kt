package com.example.jokeapp.data.net

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel() = service.getQuote()
}