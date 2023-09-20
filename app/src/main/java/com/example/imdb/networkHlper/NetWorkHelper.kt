package com.example.imdb.networkHlper

import com.example.imdb.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

object NetWorkHelper {

    const val APPLICATION_JSON: String = "application/json"
    const val ACCEPT_HEADER = "accept"
    const val AUTHORIZATION_HEADER = "Authorization"
    const val API_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NjZiMDhlMGYwZjVkOWQ5YmE2MDg5YTY3NTM3NDMzYyIsInN1YiI6IjVlM2JmOGZjMGMyNzEwMDAxYTc4MTY5OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YGsLu0H3tAkAh8ltiT4EN1GMlWTZwGf4zOusHc_AAlw"

    fun getPopularMovie(responseCallback: ApiResponseCallback<Results>) {
        RetrofitClient.createRetrofit().getPopularMovies(getMapHeaders()).enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful && response.body() != null) {
                    responseCallback.onSuccess(response.body()!!)
                } else {
                    responseCallback.onFailure("print ")
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                responseCallback.onFailure("print ")
            }
        })
    }

    fun getTopRatedMovie(responseCallback: ApiResponseCallback<Results>) {
        RetrofitClient.createRetrofit().getTopRatedMovie(getMapHeaders()).enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (response.isSuccessful && response.body() != null) {
                    responseCallback.onSuccess(response.body()!!)
                } else {
                    responseCallback.onFailure("print ")
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                responseCallback.onFailure("print ")
            }
        })
    }

    suspend fun searchMovie(query: String): Results{
       return RetrofitClient.createRetrofit().searchMovie(getMapHeaders(), query)
    }


    private fun getMapHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers[ACCEPT_HEADER] = APPLICATION_JSON
        headers[AUTHORIZATION_HEADER] = API_TOKEN
        return headers
    }

}

interface ApiResponseCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(errorMessage: String)
}