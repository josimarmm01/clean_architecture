package com.example.josimar.presentetion.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.josimar.databinding.FragmentMoviesBinding
import com.example.josimar.framework.imageloader.ImageLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoad

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMoviesBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMoviesAdapter()
        observeInitialLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesPaginData(1).collect { pagingData ->
                    movieAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initMoviesAdapter() {

        movieAdapter = MovieAdapter(imageLoader) { idMovie ->
            val directions = MoviesFragmentDirections
                .actionMoviesFragmentToDetailMovieFragment(idMovie)

            findNavController().navigate(directions)
        }

        with (binding.recyclerviewMovies) {
            //scrollToPosition(0)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun observeInitialLoadState() {
            lifecycleScope.launch {
                movieAdapter.loadStateFlow.collectLatest { loadState ->
                    binding.fliperMovies.displayedChild =
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            setSinnerVisibility(true)
                            FLIPPER_CHILD_LOADING
                        }
                        is LoadState.NotLoading -> {
                            setSinnerVisibility(false)
                            FLIPPER_CHILD_MOVIE
                        }
                        is LoadState.Error -> {
                            setSinnerVisibility(false)
                            binding.includeViewMoviesErrorState.btnRetry.setOnClickListener {
                                movieAdapter.refresh()
                            }
                            FLIPPER_CHILD_ERROR
                        }
                    }
            }
        }
    }

    private fun setSinnerVisibility(visibility: Boolean) {
        binding.includeViewMoviesLoadindState.shimmerMovie.run {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_MOVIE = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }

}
