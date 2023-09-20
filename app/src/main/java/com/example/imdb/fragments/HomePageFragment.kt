package com.example.imdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.adapters.MoviesAdapter
import com.example.imdb.adapters.MoviesHorizontalAdapter
import com.example.imdb.adapters.OpenMovieDetailsListener
import com.example.imdb.databinding.FragmentHomePageBinding
import com.example.imdb.models.Movie
import com.example.imdb.viewModel.MainViewModel


class HomePageFragment : Fragment(), OpenMovieDetailsListener {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel by activityViewModels<MainViewModel>()

    private var popularMoviesAdapter : MoviesHorizontalAdapter? = null
    private var topRatedMoviesAdapter : MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getPopularMovie().observe(viewLifecycleOwner) {
            popularMoviesAdapter?.movieList = it
            popularMoviesAdapter?.notifyDataSetChanged()
        }

        viewModel.getTopRatedMovie().observe(viewLifecycleOwner) {
            topRatedMoviesAdapter?.movieList = it
            topRatedMoviesAdapter?.notifyDataSetChanged()
        }
    }

    private fun initAdapters() {
        popularMoviesAdapter = MoviesHorizontalAdapter(viewModel.getPopularMovie().value, this)
        binding.HPFPopularMoviesRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.HPFPopularMoviesRV.adapter = popularMoviesAdapter

        topRatedMoviesAdapter = MoviesAdapter(viewModel.getTopRatedMovie().value, this)
        binding.HPFTopRatedMoviesRV.layoutManager = LinearLayoutManager(context)
        binding.HPFTopRatedMoviesRV.adapter = topRatedMoviesAdapter
    }

    override fun openMovieDetails(movie: Movie) {
        MovieDetailsPage(movie).show(
            childFragmentManager, null
        )
    }

}