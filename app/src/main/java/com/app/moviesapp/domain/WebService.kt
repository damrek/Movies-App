package com.app.moviesapp.domain

import com.app.moviesapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("discover/movie")
    suspend fun getPopularMovies(@Query("primary_release_year") yearRelease: String, @Query("sort_by") sortType: String, @Query("language") lang: String): MovieResponse

    @GET("search/movie")
    suspend fun getMoviesSearch(@Query("query") query: String, @Query("language") lang: String, @Query("include_adult") adult: Boolean): MovieResponse

}