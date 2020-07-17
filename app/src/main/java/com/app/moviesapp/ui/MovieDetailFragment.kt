package com.app.moviesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.moviesapp.AppDatabase
import com.app.moviesapp.R
import com.app.moviesapp.data.DataSourceImpl
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.domain.RepoImpl
import com.app.moviesapp.ui.viewmodel.MainViewModel
import com.app.moviesapp.ui.viewmodel.VMFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSourceImpl(
        AppDatabase.getDatabase(requireActivity().applicationContext)))) }

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
        movie_votes_average_detail.text = movie.voteAverage.toString()
        movie_release_year_detail.text = movie.releaseDate.split("-")[0]
        btn_save_movie.setOnClickListener {
            viewModel.saveMovie(MovieEntity(movie.movieId, movie.releaseDate,movie.image,movie.name,movie.description,movie.voteAverage,movie.adult))
            Toast.makeText(requireContext(), "Movie saved on favorites!", Toast.LENGTH_SHORT).show()
        }
    }

}