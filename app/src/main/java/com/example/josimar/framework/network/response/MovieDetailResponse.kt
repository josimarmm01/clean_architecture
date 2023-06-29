package com.example.josimar.framework.network.response

import com.example.core.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(@SerializedName("id") val id: Int,
                               @SerializedName("original_title") val titleMovie: String,
                               @SerializedName("overview") val overview: String,
                               @SerializedName("poster_path") val posterPath: String,
                               @SerializedName("backdrop_path") val backdropPath: String,
                               @SerializedName("release_date") val releaseDate: String,
                               @SerializedName("vote_count") val voteCount: String)

fun MovieDetailResponse.toMovieDetailModel(): MovieDetail {
    return MovieDetail(
        idMovie = this.id,
        name = this.titleMovie,
        overview = this.overview,
        posterPath = "https://image.tmdb.org/t/p/w780/${this.posterPath}",
        backdropPath = "https://image.tmdb.org/t/p/w780/${this.backdropPath}",
        releaseDate = releaseDate,
        voteCount = voteCount
    )
}
