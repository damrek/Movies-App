package com.app.moviesapp.domain

import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource

interface Repo {
    fun getMoviesList() : Resource<List<Movie>>
}