package com.app.moviesapp.data

import android.content.SharedPreferences
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.domain.DataSource
import com.app.moviesapp.domain.MoviesDao
import com.app.moviesapp.domain.WebService
import com.app.moviesapp.vo.Resource
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val webService: WebService,
    private val sharedPreferences: SharedPreferences
) : DataSource {

    override suspend fun getPopularMovies(
        yearRelease: String,
        sortType: String
    ): Resource<List<Movie>> {
        return Resource.Success(webService.getPopularMovies(yearRelease, sortType, sharedPreferences.getString("language", "en")!!).results)
    }

    override suspend fun getMoviesBySearch(query: String): Resource<List<Movie>> {
        return Resource.Success(webService.getMoviesSearch(query, sharedPreferences.getString("language", "en")!!, true).results)
    }

    override suspend fun insertMovieIntoRoom(movie: MovieEntity) {
        moviesDao.insertFavorite(movie)
    }

    override suspend fun getMoviesFavorites(): Resource<List<MovieEntity>> {
        return Resource.Success(moviesDao.getAllFavoriteMovies())
    }

    override suspend fun deleteMovieFromRoom(movie: MovieEntity) {
        moviesDao.deleteMovie(movie)
    }

}