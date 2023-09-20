package com.example.imdb.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.imdb.models.Movie
import com.example.imdb.models.Results
import com.example.imdb.models.ServerMovie
import com.example.imdb.networkHlper.ApiResponseCallback
import com.example.imdb.networkHlper.NetWorkHelper

object Reposetory {
    var popularMovie: MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    var topRatedMovie: MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    fun initData(){

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
    suspend fun searchMovie(query : String): ArrayList<Movie>  {
       return convertServerMovie(NetWorkHelper.searchMovie(query).results)
    }

    private fun convertServerMovie(results: ArrayList<ServerMovie>?): ArrayList<Movie> {
        val movies : ArrayList<Movie> = ArrayList()
        results?.forEach {it.id?.let { id ->
            movies.add(
                Movie(
                    movieId = it.id!!,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    title = it.title,
                )
            )
        }
        }
        return movies
    }
}