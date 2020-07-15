package com.app.moviesapp.data

import com.app.App
import com.app.moviesapp.AppDatabase
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.vo.Resource
import com.app.moviesapp.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getPopularMovies(yearRelease:String, sortType:String):Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webservice.getPopularMovies(yearRelease, sortType, App.getSharedPreferences().getString("language", "en")!!).results)
    }

    suspend fun getMoviesBySearch(query:String):Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webservice.getMoviesSearch(query, App.getSharedPreferences().getString("language", "en")!!).results)
    }

    suspend fun insertMovieIntoRoom(movie:MovieEntity){
        appDatabase.movieDao().insertFavorite(movie)
    }

    suspend fun getMoviesFavorites(): Resource<List<MovieEntity>> {
        return Resource.Success(appDatabase.movieDao().getAllFavoriteMovies())
    }

    suspend fun deleteMovieFromRoom(movie:MovieEntity){
        appDatabase.movieDao().deleteMovie(movie)
    }

}