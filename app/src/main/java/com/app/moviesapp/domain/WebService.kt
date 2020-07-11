package com.app.moviesapp.domain

import com.app.moviesapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("discover/movie")
    suspend fun getPopularMovies(@Query("sort_by") sortType: String, @Query("language") lang: String): MovieResponse

}