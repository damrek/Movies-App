package com.app.moviesapp.domain

import com.app.moviesapp.data.DataSource
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {

    override suspend fun getMoviesList(sortType:String, lang:String): Resource<List<Movie>> {
        return dataSource.getPopularMovies(sortType, lang)
    }

}