package com.app.moviesapp.domain

import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource

interface Repo {
    suspend fun getMoviesList(yearRelease:String, sortType:String, lang:String = "en") : Resource<List<Movie>>
    suspend fun getMoviesListSearch(query:String, lang:String = "en") : Resource<List<Movie>>
}