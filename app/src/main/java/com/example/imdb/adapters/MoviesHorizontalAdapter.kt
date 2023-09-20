package com.example.imdb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.MovieHorizontalItemBinding
import com.example.imdb.models.Movie
import com.squareup.picasso.Picasso

const val baseUrl = "https://image.tmdb.org/t/p/"
const val imageHorizontalSizePoster = "w200"
const val imageVerticalSizePoster = "w500"

class MoviesHorizontalAdapter(
    var movieList: List<Movie>?,
    private var openMovieDetailsListener: OpenMovieDetailsListener?
) : RecyclerView.Adapter<MoviesHorizontalAdapter.MovieItemViewHolder>() {


    override fun getItemCount(): Int { return movieList?.size ?: 0 }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        movieList?.get(position)?.let { movieItem ->
            holder.binding.MIMovieNameTV.text = movieItem.title

            Picasso.get()
                .load("$baseUrl$imageHorizontalSizePoster${movieItem.posterPath}")
                .into(holder.binding.MIMovieImageIV)

            holder.binding.root.setOnClickListener {
                openMovieDetailsListener?.openMovieDetails(movieItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemBinding = MovieHorizontalItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieItemViewHolder(itemBinding)
    }


    class MovieItemViewHolder(val binding: MovieHorizontalItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}

interface OpenMovieDetailsListener {
    fun openMovieDetails(movie: Movie)
}