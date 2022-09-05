package com.app.myapplication.feature.data.remote.dto

import com.app.myapplication.feature.domain.model.Company
import com.app.myapplication.feature.domain.model.Country
import com.app.myapplication.feature.domain.model.Gender
import com.app.myapplication.feature.domain.model.MovieDetails
import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName(value = "id") val id: Int? = 0,
    @SerializedName(value = "revenue") val revenue: Float? = 0F,
    @SerializedName(value = "runtime") val runtime: String? = "0",
    @SerializedName(value = "vote_average") val voteAverage: Float? = 0F,
    @SerializedName(value = "popularity") val popularity: Float? = 0F,
    @SerializedName(value = "vote_count") val voteCount: Float? = 0F,
    @SerializedName(value = "budget") val budget: Float? = 0F,

    @SerializedName(value = "title") val title: String? = "",
    @SerializedName(value = "original_title") val originalTitle: String? = "",
    @SerializedName(value = "backdrop_path") val background: String? = "",
    @SerializedName(value = "homepage") val homepage: String? = "",
    @SerializedName(value = "overview") val overview: String? = "",
    @SerializedName(value = "poster_path") val foreground: String? = "",
    @SerializedName(value = "release_date") val releaseDate: String? = "",
    @SerializedName(value = "tagline") val slogan: String? = "",

    @SerializedName(value = "genres") val genres: List<GenderDto>? = emptyList(),
    @SerializedName(value = "production_companies") val companies: List<CompanyDto>? = emptyList(),
    @SerializedName(value = "production_countries") val countries: List<CountryDto>? = emptyList(),

    @SerializedName(value = "adult") val adult: Boolean? = false,
) {
    data class GenderDto(
        @SerializedName(value = "id") val id: Int? = 0,
        @SerializedName(value = "name") val name: String? = "",
        @SerializedName(value = "image") val image: String? = ""
    ) {
        val gender: Gender
            get() = Gender(
                id ?: 0,
                name ?: "",
                image ?: ""
            )
    }

    data class CompanyDto(
        @SerializedName(value = "id") val id: Long? = 0,
        @SerializedName(value = "name") val name: String? = "",
        @SerializedName(value = "logo_path") val logo: String? = "",
        @SerializedName(value = "origin_country") val originCountry: String? = ""
    ) {
        val company: Company
            get() = Company(
                id ?: 0,
                name ?: "",
                logo ?: "",
                originCountry ?: ""
            )
    }

    data class CountryDto(
        @SerializedName(value = "iso_3166_1") val language: String? = "",
        @SerializedName(value = "name") val name: String? = ""
    ) {
        val country: Country
            get() = Country(
                language ?: "",
                name ?: ""
            )
    }

    val movie: MovieDetails
        get() = MovieDetails(
            id ?: 0,
            revenue ?: 0F,
            runtime ?: "0",
            voteAverage ?: 0F,
            popularity ?: 0F,
            voteCount ?: 0F,
            budget ?: 0F,
            title ?: "",
            originalTitle ?: "",
            background ?: "",
            homepage ?: "",
            overview ?: "",
            foreground ?: "",
            releaseDate ?: "",
            slogan ?: "",
            genres?.map { it.gender } ?: emptyList(),
            companies?.map { it.company } ?: emptyList(),
            countries?.map { it.country } ?: emptyList(),
            adult ?: false
        )
}