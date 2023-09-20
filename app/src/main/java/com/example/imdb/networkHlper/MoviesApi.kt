package com.example.imdb.networkHlper

import com.example.imdb.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface MoviesApi {

        @GET("movie/popular")
        fun getPopularMovies(@HeaderMap headerMap: Map<String, String>): Call<Results>

        @GET("movie/top_rated")
        fun getTopRatedMovie(@HeaderMap headerMap: Map<String, String>): Call<Results>

        @GET("search/movie")
        suspend fun searchMovie(
                @HeaderMap headerMap: Map<String, String>,
                @Query("query") query: String,
        ): Results
}
