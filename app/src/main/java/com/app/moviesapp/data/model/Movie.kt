package com.app.moviesapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name="poster_path")
    val image: String = "",
    @Json(name="title")
    val name: String = "",
    @Json(name="overview")
    val description: String = ""
) : Parcelable

@Parcelize
data class MovieResponse(
    val results: List<Movie>
):Parcelable