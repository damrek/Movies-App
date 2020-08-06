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
import java.util.*

class MainAdapter(
    private val context: Context, private val moviesList: MutableList<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieCLick(movie: Movie, position: Int)
    }

    fun deleteMovie(position: Int){
        moviesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun sortMovies(type:String){
        when(type){
            "Alphabetically" ->  moviesList.sortBy { it -> it.name  }
            "Votes average" ->  moviesList.sortByDescending { it -> it.voteAverage  }
        }
        notifyDataSetChanged()
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
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${item.image}")
                .placeholder(R.drawable.ic_baseline_broken_image_24).fitCenter()
                .into(itemView.movie_img)
            if(item.isAdult){
                itemView.movie_adult_img.visibility =  View.VISIBLE
            }
            itemView.movie_title.text = item.name
            itemView.movie_description.text = item.description
            itemView.setOnClickListener { itemClickListener.onMovieCLick(item, position) }
            itemView.movie_votes_average.text = item.voteAverage.toString()
            itemView.movie_release_year.text = item.releaseDate.split("-")[0]
        }
    }
}