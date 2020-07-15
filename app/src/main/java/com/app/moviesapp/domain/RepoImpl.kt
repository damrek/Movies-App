package com.app.moviesapp.domain

import com.app.moviesapp.data.DataSource
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {

    override suspend fun getMoviesList(yearRelease:String, sortType:String): Resource<List<Movie>> {
        return dataSource.getPopularMovies(yearRelease, sortType)
    }

    override suspend fun getMoviesListSearch(query:String): Resource<List<Movie>> {
        return dataSource.getMoviesBySearch(query)
    }

    override suspend fun getFavoriteMovies(): Resource<List<MovieEntity>> {
        return dataSource.getMoviesFavorites()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        dataSource.insertMovieIntoRoom(movie)
    }

    override suspend fun deleteMovie(movie: MovieEntity) {
        dataSource.deleteMovieFromRoom(movie)
    }
}