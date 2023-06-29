package com.example.josimar.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(@SerializedName("page") val page: Int,
                               @SerializedName("results") val results: List<MovieResponse>,
                               @SerializedName("total_pages") val totalPages: Int,
                               @SerializedName("total_results") val totalResults: Int)