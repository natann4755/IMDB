package com.example.imdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.R
import com.example.imdb.adapters.MoviesAdapter
import com.example.imdb.adapters.MoviesHorizontalAdapter
import com.example.imdb.databinding.FragmentFavouritesBinding
import com.example.imdb.databinding.FragmentHomePageBinding
import com.example.imdb.models.Movie
import com.example.imdb.room.AppDatabase
import com.example.imdb.viewModel.MainViewModel

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel by activityViewModels<MainViewModel>()

    private var favouritesMoviesAdapter : MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initViewModel()
    }

    private fun initViewModel() {
    }

    private fun initAdapter() {
        context?.let {
            favouritesMoviesAdapter = MoviesAdapter(
//                AppDatabase.getInstance()?.favouritesDao()
//                    .getAllFavourites().value as ArrayList<Movie>?
            null
            )
            binding.FFFavouritesRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.FFFavouritesRV.adapter = favouritesMoviesAdapter
        }
    }
}