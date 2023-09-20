package com.example.imdb.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie (

    @PrimaryKey(autoGenerate = true)
    var movieId: Int,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var creationDate: Long? = null
)