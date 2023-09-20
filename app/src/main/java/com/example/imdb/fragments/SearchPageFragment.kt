package com.example.imdb.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdb.R
import com.example.imdb.adapters.MoviesHorizontalAdapter
import com.example.imdb.adapters.OpenMovieDetailsListener
import com.example.imdb.databinding.FragmentHomePageBinding
import com.example.imdb.databinding.FragmentSearchPageBinding
import com.example.imdb.models.Movie
import com.example.imdb.viewModel.MainViewModel
import com.example.imdb.viewModel.Reposetory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchPageFragment : Fragment(), OpenMovieDetailsListener {
    private lateinit var binding: FragmentSearchPageBinding
    private var searchMoviesAdapter : MoviesHorizontalAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        initSearchEditText()
    }

    private fun initSearchEditText() {
        binding.FSPEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 if (s.toString().isNotEmpty()){
                     var searchResults : ArrayList<Movie>? = null
                     CoroutineScope(Dispatchers.IO).launch{
                         searchResults = Reposetory.searchMovie(s.toString())
                         withContext(Dispatchers.Main){
                             searchMoviesAdapter?.movieList = searchResults
                             searchMoviesAdapter?.notifyDataSetChanged()
                         }
                     }
                 }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initAdapters() {
        searchMoviesAdapter = MoviesHorizontalAdapter(null, this)
        binding.FSPSearchMoviesRV.layoutManager = GridLayoutManager(context, 3)
        binding.FSPSearchMoviesRV.adapter = searchMoviesAdapter
    }

    override fun openMovieDetails(movie: Movie) {
        MovieDetailsPage(movie).show(
            childFragmentManager, null
        )
    }
}