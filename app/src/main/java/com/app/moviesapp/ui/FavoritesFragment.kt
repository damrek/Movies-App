package com.app.moviesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.moviesapp.AppDatabase
import com.app.moviesapp.R
import com.app.moviesapp.data.DataSourceImpl
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.domain.RepoImpl
import com.app.moviesapp.ui.viewmodel.MainViewModel
import com.app.moviesapp.ui.viewmodel.VMFactory
import com.app.moviesapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), MainAdapter.OnMovieClickListener {

    private lateinit var adapter: MainAdapter

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSourceImpl(
                    AppDatabase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val list = result.data.map {
                        Movie(
                            it.movieId,
                            it.releaseDate,
                            it.image,
                            it.name,
                            it.description,
                            it.voteAverage,
                            it.adult
                        )
                    }.toMutableList()

                    adapter = MainAdapter(
                        requireContext(), list, this
                    )

                    rv_movies_favorites.adapter = adapter
                    (activity as AppCompatActivity).supportActionBar?.title = String.format("Favorite movies ("+adapter.itemCount.toString()+")")
                }
                is Resource.Failure -> {
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rv_movies_favorites.layoutManager = LinearLayoutManager(requireContext())
        rv_movies_favorites.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onMovieCLick(movie: Movie, position: Int) {
        viewModel.deleteMovie(
            MovieEntity(
                movie.movieId,
                movie.releaseDate,
                movie.image,
                movie.name,
                movie.description,
                movie.voteAverage,
                movie.adult
            )
        )
        adapter.deleteMovie(position)
        (activity as AppCompatActivity).supportActionBar?.title = String.format("Favorite movies ("+adapter.itemCount.toString()+")")
        Toast.makeText(requireContext(), "Movie deleted from favorites!", Toast.LENGTH_SHORT).show()
    }
}