package com.example.flow.data.movie.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("lastBuildDate") var lastBuildDate: String,
    @SerializedName("total") var total: Int,
    @SerializedName("start") var start: Int,
    @SerializedName("display") var display: Int,
    @SerializedName("items") var items: List<Movie>
)
