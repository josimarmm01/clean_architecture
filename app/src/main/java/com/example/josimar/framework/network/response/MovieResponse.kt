package com.example.josimar.framework.network.response

import com.example.core.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("id") val id: Int,
                         @SerializedName("original_title") val titleMovie: String,
                         @SerializedName("overview") val overview: String,
                         @SerializedName("poster_path") val posterPath: String,
                         @SerializedName("backdrop_path") val backdropPath: String,
                         @SerializedName("release_date") val releaseDate: String,
                         @SerializedName("vote_count") val voteCount: String)

fun MovieResponse.toMoviesModel(): Movie {
    return Movie(
        idMovie = this.id,
        name = this.titleMovie,
        igmUrl = "https://image.tmdb.org/t/p/w342/${this.posterPath}"
    )
}