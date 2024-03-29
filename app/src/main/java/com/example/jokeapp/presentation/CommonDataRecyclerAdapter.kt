package com.example.jokeapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.FailedCommonUiModel
import com.example.jokeapp.R
import com.example.jokeapp.core.presentation.CommonCommunication
import com.example.jokeapp.presentation.customViews.CorrectImageButton
import com.example.jokeapp.presentation.customViews.CorrectTextView

class CommonDataRecyclerAdapter<T>(
    private val listener: FavouriteItemClickListener<T>,
    private val communication: CommonCommunication<T>
    ) :
    RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder<T>>() {

    override fun getItemViewType(position: Int) = when (communication.getList()[position]) {
        is FailedCommonUiModel -> 0
        else -> 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        notifyDataSetChanged()
    }

    fun update(pair: Pair<Boolean, Int>) {
        if (pair.first) {
            notifyItemInserted(pair.second)
        } else {
            notifyItemRemoved(pair.second)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<T> {
        val emptyList = viewType == 0
        val view = LayoutInflater.from(parent.context).inflate(
            if (emptyList)
                R.layout.no_favourite_item
            else
                R.layout.common_data_item,
            parent, false
        )
        return if (emptyList) EmptyFavouritesViewHolder(view) else CommonDataViewHolder.Base(view, listener)
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder<T>, position: Int) {
        holder.bind(communication.getList()[position])
    }

    override fun getItemCount() = communication.getList().size


    abstract class CommonDataViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)

        open fun bind(model: CommonUiModel<T>) = model.show(textView)

        class Base<T>(view: View, private val listener:  FavouriteItemClickListener<T>) :
            CommonDataViewHolder<T>(view) {
                private val iconView = itemView.findViewById<CorrectImageButton>(R.id.changeButton)
                override fun bind(model: CommonUiModel<T>) {
                    super.bind(model)
                    iconView.setOnClickListener {
                        model.change(listener)
                    }
            }
        }
    }

    inner class EmptyFavouritesViewHolder<T>(view: View): CommonDataViewHolder<T>(view)

    interface FavouriteItemClickListener<T> {
        fun change(id: T)
    }
}