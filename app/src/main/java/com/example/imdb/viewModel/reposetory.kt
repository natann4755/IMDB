package com.example.imdb.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.imdb.models.Movie
import com.example.imdb.models.Results
import com.example.imdb.models.ServerMovie
import com.example.imdb.networkHlper.ApiResponseCallback
import com.example.imdb.networkHlper.NetWorkHelper
import com.example.imdb.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Repository {
    var popularMovie: MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    var topRatedMovie: MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    fun initData() {

        NetWorkHelper.getPopularMovie(object : ApiResponseCallback<Results> {
            override fun onSuccess(data: Results) {
                popularMovie.value = convertServerMovie(data.results)
            }

            override fun onFailure(errorMessage: String) {
                // print message
            }
        })

        NetWorkHelper.getTopRatedMovie(object : ApiResponseCallback<Results> {
            override fun onSuccess(data: Results) {
                topRatedMovie.value = convertServerMovie(data.results)
            }

            override fun onFailure(errorMessage: String) {
                // print message
            }
        })
    }

    suspend fun searchMovie(query: String): ArrayList<Movie> {
        return convertServerMovie(NetWorkHelper.searchMovie(query).results)
    }

    fun removeCacheImagesAfterDay(context: Context) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            val retentionPeriod = 24 * 60 * 60 * 1000 // 1 day
            val retentionThreshold = System.currentTimeMillis() - retentionPeriod
            AppDatabase.getInstance(context).favouritesDao().getAllFavourites().forEach {
                if (it.creationDate != null && it.creationDate!! < retentionThreshold) {
                    AppDatabase.getInstance(context).favouritesDao().deleteFavouriteMovie(it)
                }
            }
        }
    }

    private fun convertServerMovie(results: ArrayList<ServerMovie>?): ArrayList<Movie> {
        val movies: ArrayList<Movie> = ArrayList()
        results?.forEach { serverMovie ->
            serverMovie.id?.let {
                movies.add(
                    Movie(
                        movieId = serverMovie.id!!,
                        overview = serverMovie.overview,
                        popularity = serverMovie.popularity,
                        posterPath = serverMovie.posterPath,
                        title = serverMovie.title,
                    )
                )
            }
        }
        return movies
    }
}