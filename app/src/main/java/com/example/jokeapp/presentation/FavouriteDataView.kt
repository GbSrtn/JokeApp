package com.example.jokeapp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.jokeapp.R
import com.example.jokeapp.core.presentation.CommonItemViewModel
import com.example.jokeapp.presentation.customViews.CorrectButton
import com.example.jokeapp.presentation.customViews.CorrectImageButton
import com.example.jokeapp.presentation.customViews.CorrectProgress
import com.example.jokeapp.presentation.customViews.CorrectTextView

class FavouriteDataView : LinearLayout {
    private lateinit var checkBox : CheckBox
    private lateinit var textView : CorrectTextView
    private lateinit var changeButton : CorrectImageButton
    private lateinit var actionButton : CorrectButton
    private lateinit var progress: CorrectProgress

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        gravity = CENTER
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favourite_data_view, this, true)
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.textView)
        changeButton = linear.findViewById(R.id.changeButton)
        progress = getChildAt(2) as CorrectProgress
        progress.visibility = View.INVISIBLE
        actionButton = getChildAt(3) as CorrectButton

        context.theme.obtainStyledAttributes(attrs, R.styleable.FavouriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavouriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavouriteDataView_checkBoxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }
    }

    fun listenChanges(block: (checked: Boolean) -> Unit) =
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            block.invoke(isChecked)
        }

    fun handleChangeButton(block: () -> Unit) = changeButton.setOnClickListener {
        block.invoke()
    }

    fun handleActionButton(block: () -> Unit) = actionButton.setOnClickListener {
        block.invoke()
    }

    fun show(state: State) = state.show(progress, actionButton,textView,changeButton)

    fun linkWith(commonViewModel: CommonItemViewModel) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            commonViewModel.chooseFavourites(isChecked)
        }
        changeButton.setOnClickListener {
            commonViewModel.changeItemStatus()
        }
        actionButton.setOnClickListener {
            commonViewModel.getItem()
        }
    }
}