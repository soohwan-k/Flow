package com.example.flow.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieResponse (
    @SerializedName("lastBuildDate") var lastBuildDate: String,
    @SerializedName("total") var total: Int,
    @SerializedName("start") var start: Int,
    @SerializedName("display") var display: Int,
    @SerializedName("items") var items: List<Movie>
)

//    val Response: String,
//    val Search: List<Movie>,
//    val totalResults: String