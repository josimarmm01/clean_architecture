package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.core.data.repository.MoviesRepository
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.usecase.base.PaginUseCase
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetMovieDetailUseCase {

    operator fun invoke(params: GetMoviesParams): Flow<ResultStatus<MovieDetail>>
    data class GetMoviesParams(val idMovie: Int)
}

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMovieDetailUseCase, UseCase<GetMovieDetailUseCase.GetMoviesParams, MovieDetail>() {

    override suspend fun doWork(
        params: GetMovieDetailUseCase.GetMoviesParams): ResultStatus<MovieDetail> {

        val movieDetail = moviesRepository.getDetailMovie(params.idMovie)

        return ResultStatus.Success(movieDetail)
    }

}
