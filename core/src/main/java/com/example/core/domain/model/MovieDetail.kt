package com.example.core.domain.model

data class MovieDetail(val idMovie: Int,
                       val name: String,
                       val overview: String,
                       val posterPath: String,
                       val backdropPath: String,
                       val releaseDate: String,
                       val voteCount: String)
