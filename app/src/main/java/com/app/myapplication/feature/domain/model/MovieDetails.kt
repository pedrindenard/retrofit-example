package com.app.myapplication.feature.domain.model

import java.io.Serializable

data class MovieDetails(
    val id: Int,
    val revenue: Float,
    val runtime: String,
    val voteAverage: Float,
    val popularity: Float,
    val voteCount: Float,
    val budget: Float,
    val title: String,
    val originalTitle: String,
    val background: String,
    val homepage: String,
    val overview: String,
    val foreground: String,
    val releaseDate: String,
    val slogan: String,
    val genres: List<Gender>,
    val companies: List<Company>,
    val countries: List<Country>,
    val adult: Boolean
) : Serializable {

    val genre: String get() = genres.joinToString(",\n") { it.name }

    val company: String get() = companies.joinToString(",\n") { it.name }

    val country: String get() = countries.joinToString(",\n") { it.name }
}