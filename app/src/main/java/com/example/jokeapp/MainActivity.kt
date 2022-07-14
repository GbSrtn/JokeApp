package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.jokeapp.Base.BaseModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)

        val changeButton = findViewById<ImageView>(R.id.changeButton)
        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }

        progressBar.visibility = View.INVISIBLE


        val button = findViewById<Button>(R.id.actionButton)
        button.setOnClickListener{
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseDataSource(isChecked)
        }


        viewModel.init(object : DataCallback {
            override fun provideText(text: String) {
                button.isEnabled =true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconResId(id: Int) {
                changeButton.setImageResource(id)
            }

        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

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

        viewModel = ViewModel(
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
            )
        )
    }
}

