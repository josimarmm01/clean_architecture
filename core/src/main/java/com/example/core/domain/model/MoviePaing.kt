package com.example.core.domain.model

data class MoviePaging(
    val page: Int,
    val total: Int,
    val movies: List<Movie>
)
