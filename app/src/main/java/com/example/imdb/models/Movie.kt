package com.example.imdb.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Movie (

//    val id: Long = 0,
    @PrimaryKey(autoGenerate = true)
    var movieId: Int,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var title: String? = null,
)