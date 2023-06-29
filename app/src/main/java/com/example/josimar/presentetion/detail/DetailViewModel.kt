package com.example.josimar.presentetion.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.MovieDetail
import com.example.core.usecase.AddFavoritesUserCase
import com.example.core.usecase.GetMovieDetailUseCase
import com.example.josimar.R
import com.example.josimar.presentetion.extention.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addFavoritesUserCase: AddFavoritesUserCase): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private val _favoriteUiState = MutableLiveData<FavoritesUiState>()
    val favoriteUiState: LiveData<FavoritesUiState> get() = _favoriteUiState

    init {
        _favoriteUiState.value = FavoritesUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
    }

    fun getDetailMovie(idMovie: Int) = viewModelScope.launch {
        getMovieDetailUseCase(GetMovieDetailUseCase.GetMoviesParams(idMovie))
            .watchStatus(
                loading = {
                    _uiState.value = UiState.Loading
                },
                success = {data ->
                    _uiState.value = UiState.Success(data)
                },
                error = {
                    _uiState.value = UiState.Error
                }
            )
    }

    fun updateFavorite(movie: MovieDetail)  = viewModelScope.launch {
        addFavoritesUserCase.invoke(
            AddFavoritesUserCase.Params(
                movie.idMovie,
                movie.name,
                movie.posterPath,
                1
            )
        ).watchStatus(
            loading = {
                _favoriteUiState.value = FavoritesUiState.Loading
            },
            success = {
                _favoriteUiState.value = FavoritesUiState.FavoriteIcon(R.drawable.ic_favorite_checked)
            },
            error = {
            }
        )
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val movieDetail: MovieDetail) : UiState()
        object Error: UiState()
    }

    sealed class FavoritesUiState {
        object Loading : FavoritesUiState()
        class FavoriteIcon(val id: Int) : FavoritesUiState()
    }

}