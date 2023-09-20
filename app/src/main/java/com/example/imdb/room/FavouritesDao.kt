package com.example.imdb.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.imdb.models.Movie

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM Movie")
    fun getAllFavourites(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteMovie(movie: Movie)

    @Delete
    fun deleteFavouriteMovie(movie: Movie)
}