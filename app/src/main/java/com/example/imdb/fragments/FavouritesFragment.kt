package com.example.imdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.adapters.MoviesAdapter
import com.example.imdb.databinding.FragmentFavouritesBinding
import com.example.imdb.models.Movie
import com.example.imdb.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding

    private var favouritesMoviesAdapter: MoviesAdapter? = null

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
    }


    private fun initAdapter() {
        context?.let {
            favouritesMoviesAdapter = MoviesAdapter(null)
            binding.FFFavouritesRV.layoutManager =
                LinearLayoutManager(context)
            binding.FFFavouritesRV.adapter = favouritesMoviesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            CoroutineScope(Dispatchers.IO).launch {
                favouritesMoviesAdapter?.movieList = AppDatabase.getInstance(it).favouritesDao()
                    .getAllFavourites() as ArrayList<Movie>?

                withContext(Dispatchers.Main) {
                    favouritesMoviesAdapter?.notifyDataSetChanged()
                }
            }
        }
    }
}