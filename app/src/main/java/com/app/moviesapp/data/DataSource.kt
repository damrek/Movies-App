package com.app.moviesapp.data

import com.app.moviesapp.AppDatabase
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.vo.Resource
import com.app.moviesapp.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getPopularMovies(yearRelease:String, sortType:String, lang:String):Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webservice.getPopularMovies(yearRelease, sortType, lang).results)
    }

    suspend fun getMoviesBySearch(query:String, lang:String):Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webservice.getMoviesSearch(query, lang).results)
    }

    suspend fun insertMovieIntoRoom(movie:MovieEntity){
        appDatabase.movieDao().insertFavorite(movie)
    }

    suspend fun getMoviesFavorites(): Resource<List<MovieEntity>> {
        return Resource.Success(appDatabase.movieDao().getAllFavoriteMovies())
    }
}