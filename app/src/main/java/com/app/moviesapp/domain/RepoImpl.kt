package com.app.moviesapp.domain

import com.app.moviesapp.data.DataSource
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {

    override fun getMoviesList(): Resource<List<Movie>> {
        return dataSource.getMoviesList()
    }

}