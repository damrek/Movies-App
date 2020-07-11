package com.app.moviesapp.data

import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource
import com.app.moviesapp.vo.RetrofitClient

class DataSource {

    suspend fun getPopularMovies(sortType:String, lang:String):Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webservice.getPopularMovies(sortType, lang).results)
    }

}