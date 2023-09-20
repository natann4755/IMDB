package com.example.imdb.fragments

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.imdb.R
import com.example.imdb.adapters.baseUrl
import com.example.imdb.adapters.imageVerticalSizePoster
import com.example.imdb.databinding.PageMovieDetailsBinding
import com.example.imdb.models.Movie
import com.example.imdb.viewModel.MainViewModel
import com.squareup.picasso.Picasso


class MovieDetailsPage(var movie :Movie?) : DialogFragment() {
    private lateinit var binding: PageMovieDetailsBinding
    private val viewModel by activityViewModels<MainViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            super.onCreateView(inflater, container, savedInstanceState)
            binding = PageMovieDetailsBinding.inflate(LayoutInflater.from(context))
            return binding.root
        }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window
                ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData()
    }

    private fun setData() {
        binding.PMDMovieNameTV.text = "${binding.root.context.getString(R.string.name)} : ${movie?.title}"

        Picasso.get()
            .load("$baseUrl$imageVerticalSizePoster${movie?.posterPath}")
            .into(binding.PMDMovieImageIV)

        binding.PMDAddToFavoritesBU.setOnClickListener {
            movie?.let { it ->
                viewModel.insertFavouriteMovie(
                    it, binding.root.context)
            }
//                    (
//                movieId = movie?.id,
//                overview = movie?.overview,
//                popularity = movie?.popularity,
//                posterPath = movie?.posterPath,
//                title = movie?.title,
//            ),

        }
    }
}