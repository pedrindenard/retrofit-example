package com.app.myapplication.feature.data.remote.dto

import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.feature.domain.model.MovieResponse
import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName(value = "results") val results: ArrayList<MovieDto>? = arrayListOf(),
    @SerializedName(value = "total_results") val totalResults: Int? = 0,
    @SerializedName(value = "total_pages") val totalPages: Int? = 0,
    @SerializedName(value = "page") val page: Int? = 0
) {
    data class MovieDto(
        @SerializedName(value = "id") val id: Int? = 0,
        @SerializedName(value = "vote_count") val countVote: Int? = 0,
        @SerializedName(value = "vote_average") val averageVote: Float? = 0F,
        @SerializedName(value = "popularity") val popularity: Float? = 0F,

        @SerializedName(value = "title") val title: String? = "",
        @SerializedName(value = "backdrop_path") val imgBackground: String? = "",
        @SerializedName(value = "poster_path") val imgForeground: String? = "",
        @SerializedName(value = "original_title") val originalTitle: String? = "",
        @SerializedName(value = "original_language") val originalLanguage: String? = "",
        @SerializedName(value = "overview") val overview: String? = "",
        @SerializedName(value = "release_date") val releaseDate: String? = "",

        @SerializedName(value = "video") val videoAvailable: Boolean? = false,
        @SerializedName(value = "adult") val isToAdult: Boolean? = false,

        @SerializedName(value = "genre_ids") val genres: List<Int>? = emptyList()
    ) {

        val movie: Movie
            get() = Movie(
                id ?: 0,
                countVote ?: 0,
                averageVote ?: 0F,
                popularity ?: 0F,
                title ?: "",
                imgBackground ?: "",
                imgForeground ?: "",
                originalTitle ?: "",
                originalLanguage ?: "",
                overview ?: "",
                releaseDate ?: "",
                videoAvailable ?: false,
                isToAdult ?: false
            )
    }

    val result: MovieResponse
        get() = MovieResponse(
            results?.map { it.movie } as? ArrayList<Movie> ?: arrayListOf(),
            totalResults ?: 0,
            totalPages ?: 0,
            page ?: 0
        )
}