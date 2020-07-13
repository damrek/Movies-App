package com.app.moviesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.moviesapp.R
import com.app.moviesapp.data.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movie = it.getParcelable("movie")!!
            Log.d("MOVIE DETAIL", "${movie.toString()}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = movie.name
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${movie.image}")
            .fitCenter().placeholder(R.drawable.ic_baseline_broken_image_24).into(movie_img_detail)
        movie_title_detail.text = movie.name
        movie_desc_detail.text = movie.description
    }

}