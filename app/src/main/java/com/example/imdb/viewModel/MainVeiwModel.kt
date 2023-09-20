package com.example.imdb.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.models.Movie
import com.example.imdb.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getPopularMovie () : MutableLiveData<ArrayList<Movie>> { return Repository.popularMovie}
    fun getTopRatedMovie() : MutableLiveData<ArrayList<Movie>> { return Repository.topRatedMovie}

   fun initData(context: Context) {
       Repository.initData()
       Repository.removeCacheImagesAfterDay(context)

   }

    fun insertFavouriteMovie(favouriteMovie: Movie, context: Context){
        val favouriteDb = AppDatabase.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            favouriteMovie.creationDate = System.currentTimeMillis()
            favouriteDb.favouritesDao().insertFavouriteMovie(favouriteMovie)
        }
    }


}