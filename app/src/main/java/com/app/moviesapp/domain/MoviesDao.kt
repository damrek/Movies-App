package com.app.moviesapp.domain

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviesapp.data.model.MovieEntity

interface MoviesDao {

    @Query("SELECT * FROM table_movies")
    suspend fun getAllFavoriteMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie:MovieEntity)
}