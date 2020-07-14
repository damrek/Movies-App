package com.app.moviesapp.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviesapp.data.model.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM table_movies")
    suspend fun getAllFavoriteMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie:MovieEntity)
}