package com.app.moviesapp.domain

import androidx.room.*
import com.app.moviesapp.data.model.Movie
import com.app.moviesapp.data.model.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM table_movies")
    suspend fun getAllFavoriteMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie:MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}