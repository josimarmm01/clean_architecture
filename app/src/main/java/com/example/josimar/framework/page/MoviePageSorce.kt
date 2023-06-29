package com.example.josimar.framework.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.repository.MovieRemoteDataSorce
import com.example.core.domain.model.Movie

class MoviePageSorce(
    private val remoteDataSorce: MovieRemoteDataSorce,
    page: Int
    ): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {

            val currentPageNumber = params.key ?: 1
            val moviePaging = remoteDataSorce.fetchMovies(currentPageNumber)

            val nextPageNumber = moviePaging.page + 1
            val totalPage = moviePaging.total

            return LoadResult.Page(
                data = moviePaging.movies,
                prevKey = null,
                nextKey = if (currentPageNumber < totalPage) nextPageNumber else null
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
