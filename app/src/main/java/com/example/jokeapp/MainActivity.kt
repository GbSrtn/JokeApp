package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener{
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallBack {
            override fun provideText(text: String) = runOnUiThread{
                Log.d("TAG", "provideText: ")
                button.isEnabled =true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }
            
        })
//        viewModel.init(object : TextCallBack {
//            override fun provideText(text: String) = runOnUiThread {
//                Log.d("TAG", "provideText: ")
//                button.isEnabled =true
//                progressBar.visibility = View.INVISIBLE
//                Log.d("TAG", "provideText: ")
//                textView.text = text
//            }
//        })
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
        viewModel = ViewModel(BaseModel(BaseJokeService(), BaseResourseManager(this)))
        Log.d("TAG", "JokeApp onCreate: ")
    }
}

