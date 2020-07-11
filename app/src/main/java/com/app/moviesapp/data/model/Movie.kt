package com.app.moviesapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val image: String = "",
    val name: String = "",
    val description: String = ""
) : Parcelable