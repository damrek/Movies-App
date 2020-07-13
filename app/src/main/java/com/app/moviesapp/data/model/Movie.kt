package com.app.moviesapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name = "release_date")
    val releaseDate: String = "",
    @Json(name = "poster_path")
    val image: String? = "",
    @Json(name = "title")
    val name: String = "",
    @Json(name = "overview")
    val description: String = "",
    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @Json(name = "adult")
    val adult: Boolean
) : Parcelable {
    val isAdult
        get() = adult
}

@Parcelize
data class MovieResponse(
    val results: List<Movie>
) : Parcelable