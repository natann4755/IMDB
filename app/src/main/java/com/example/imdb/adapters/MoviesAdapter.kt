package com.example.imdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.MovieItemBinding
import com.example.imdb.models.Movie
import com.squareup.picasso.Picasso



class MoviesAdapter (
    var movieList: ArrayList<Movie>?,
    private var openMovieDetailsListener: OpenMovieDetailsListener? = null
    ) : RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder>() {


    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        movieList?.get(position)?.let { movieItem ->
            holder.binding.MIMovieNameTV.text = movieItem.title

            Picasso.get()
                .load("$baseUrl$imageVerticalSizePoster${movieItem.posterPath}")
                .into(holder.binding.MIMovieImageIV)

            holder.binding.root.setOnClickListener {
                openMovieDetailsListener?.openMovieDetails(movieItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val movieItemBinding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieItemViewHolder(movieItemBinding)
    }


    class MovieItemViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}