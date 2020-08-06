package com.app.moviesapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.domain.Repo
import com.app.moviesapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(private val repo: Repo) : ViewModel() {

    private val titleMovie = MutableLiveData<String>()

    fun setMovie(title: String) {
        titleMovie.value = title
    }

    init {
//        setMovie("")
    }

    val fetchMoviesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                repo.getMoviesList(
                    "2020",
                    "popularity.desc"
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    val fetchMoviesFilterList = titleMovie.distinctUntilChanged().switchMap { queryMovie ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(
                    repo.getMoviesListSearch(
                        queryMovie
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repo.insertMovie(movie)
        }
    }

    fun deleteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repo.deleteMovie(movie)
        }
    }

    fun getFavoriteMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavoriteMovies())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    override fun onCleared() {
        super.onCleared()
        setMovie("")
    }
}