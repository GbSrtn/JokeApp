package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.jokeapp.Base.BaseModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel

        val progressBar = findViewById<CorrectProgress>(R.id.progressBar)
        val button = findViewById<CorrectButton>(R.id.actionButton)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        val changeButton = findViewById<CorrectImageButton>(R.id.changeButton)

        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }

        progressBar.visibility = View.INVISIBLE


        button.setOnClickListener{
            viewModel.getJoke()
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseDataSource(isChecked)
        }

        viewModel.observe(
            this, { state ->
                state.show(progressBar, button, textView, changeButton)
        })
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityUniqueTag", "onPause: ")
    }

    override fun onResume() {
        Log.d("MainActivityUniqueTag", "onResume: ")
        super.onResume()
    }

    override fun onStart() {
        Log.d("MainActivityUniqueTag", "onStart: ")
        super.onStart()
    }
}

class JokeApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val cachedJoke = BaseCachedJoke()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider())
        val resourseManager = BaseResourseManager(this)



        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = MainViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(
                    cachedJoke,
                    cacheDataSource,
                    NoCachedJokes(resourseManager)),
                CloudResultHandler(
                    cachedJoke,
                    BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                    NoConnection(resourseManager),
                    ServiceUnavailable(resourseManager)),
                cachedJoke
            ),
            BaseCommunication()
        )
    }
}

