package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.MoviesRepository
import com.example.core.domain.model.Movie
import com.example.core.usecase.base.PaginUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetMoviesUseCase {
    operator fun invoke(params: GetMoviesParams): Flow<PagingData<Movie>>
    data class GetMoviesParams(val page: Int, val pagingConfig: PagingConfig)
}

class GetMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : PaginUseCase<GetMoviesUseCase.GetMoviesParams, Movie>(), GetMoviesUseCase {

    override fun createFlowObservable(params: GetMoviesUseCase.GetMoviesParams): Flow<PagingData<Movie>> {
        return Pager(config = params.pagingConfig) {
            moviesRepository.getMovies(params.page)
        }.flow
    }
}
