package com.app.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.moviesapp.domain.Repo
import com.app.moviesapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo: Repo):ViewModel(){

    val fetchMoviesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getMoviesList("popularity.desc"))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}