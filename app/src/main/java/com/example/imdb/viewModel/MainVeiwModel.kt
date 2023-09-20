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

    fun getPopularMovie () : MutableLiveData<ArrayList<Movie>> { return Reposetory.popularMovie}
    fun getTopRatedMovie() : MutableLiveData<ArrayList<Movie>> { return Reposetory.topRatedMovie}

   fun initData(){
       Reposetory.initData()
   }

    fun insertFavouriteMovie(favouriteMovie: Movie, context: Context){
        val favouriteDb = AppDatabase.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            favouriteDb.favouritesDao().insertFavouriteMovie(favouriteMovie)
        }
    }


}