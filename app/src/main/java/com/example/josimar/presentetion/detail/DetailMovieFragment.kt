package com.example.josimar.presentetion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.josimar.R
import com.example.josimar.databinding.FragmentDetailMovieBinding
import com.example.josimar.framework.imageloader.ImageLoad
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoad: ImageLoad

    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailMovieBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idMovie = args.idMovie

        observerUiStateData()
        observerFavoriteUiState()

        viewModel.getDetailMovie(idMovie = idMovie)
    }

    private fun observerUiStateData() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                DetailViewModel.UiState.Loading -> {}
                is DetailViewModel.UiState.Success -> {
                    binding.imageViewMovieDetailCartaz.run {
                        imageLoad.load(
                            imageView = this,
                            imageUrl = uiState.movieDetail.backdropPath,
                            fallback = R.drawable.ic_launcher_background)
                    }
                    binding.detailMovie = uiState.movieDetail

                    btnUpdateFavorite(uiState)

                }
                is DetailViewModel.UiState.Error -> {}
            }
        }
    }

    private fun btnUpdateFavorite(uiState: DetailViewModel.UiState.Success) {
        binding.imageFavoriteIcon.setOnClickListener {
            viewModel.updateFavorite(movie = uiState.movieDetail)
        }
    }

    private fun observerFavoriteUiState() {
        viewModel.favoriteUiState.observe(viewLifecycleOwner) { favoiteUiState ->
            binding.flipperFavorite.displayedChild = when (favoiteUiState) {
                DetailViewModel.FavoritesUiState.Loading -> 1
                is DetailViewModel.FavoritesUiState.FavoriteIcon -> {
                    binding.imageFavoriteIcon.setImageResource(favoiteUiState.id)
                    0
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

