package com.app.moviesapp.data

import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.vo.Resource

class DataSource {

    private val generateMoviesList = listOf(
        Movie(
            "https://upload.wikimedia.org/wikipedia/ca/thumb/9/95/Predator_Movie.jpg/200px-Predator_Movie.jpg",
            "Predator",
            "Acción en la selva"
        ),
        Movie(
            "https://i.pinimg.com/originals/87/78/11/877811585e016c719960a76372843e12.jpg",
            "Matrix",
            "Neo debe salvar el mundo"
        ),
        Movie(
            "https://cloud10.todocoleccion.online/cine-folletos-mano/tc/2017/11/27/12/104586267.jpg",
            "Matrix 2",
            "Segunda entrega de Matrix"
        )
    )

    fun getMoviesList(): Resource<List<Movie>> {
        return Resource.Success(generateMoviesList)
    }
}