package com.app.moviesapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviesapp.R
import com.app.moviesapp.base.BaseViewHolder
import com.app.moviesapp.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movies_row.view.*

class MainAdapter(
    private val context: Context, private val moviesList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieCLick(movie:Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movies_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(moviesList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        override fun bind(item: Movie, position: Int) {
            Glide.with(context).load(item.image).into(itemView.movie_img)
            itemView.movie_title.text = item.name
            itemView.movie_description.text = item.description
            itemView.setOnClickListener { itemClickListener.onMovieCLick(item)}
        }
    }
}