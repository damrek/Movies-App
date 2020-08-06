package com.app.moviesapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.moviesapp.data.model.MovieEntity
import com.app.moviesapp.domain.MoviesDao

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}