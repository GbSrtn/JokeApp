package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.jokeapp.Base.BaseModel
import com.example.jokeapp.Test.TestCacheDataSource
import com.example.jokeapp.Test.TestModel
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
            override fun provideText(text: String) = runOnUiThread{
                button.isEnabled =true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconResId(id: Int) = runOnUiThread {
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
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //viewModel = ViewModel(BaseModel(retrofit.create(JokeService::class.java), BaseResourseManager(this)))
        //viewModel = ViewModel(TestModel(BaseResourseManager(this)))
        viewModel = ViewModel(
            BaseModel(TestCacheDataSource(), BaseCloudDataSource(retrofit.create(JokeService::class.java)), BaseResourseManager(this))
        )
    }
}

