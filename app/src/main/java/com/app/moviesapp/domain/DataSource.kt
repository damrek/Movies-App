package com.app.moviesapp.domain

import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.vo.Resource

interface DataSource {
    suspend fun getPopularMovies(yearRelease: String, sortType: String): Resource<List<Movie>>
    suspend fun getMoviesBySearch(query: String): Resource<List<Movie>>
    suspend fun insertMovieIntoRoom(movie: MovieEntity)
    suspend fun getMoviesFavorites(): Resource<List<MovieEntity>>
    suspend fun deleteMovieFromRoom(movie: MovieEntity)
}