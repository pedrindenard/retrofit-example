package com.app.myapplication.feature.domain.model

import java.io.Serializable

data class MovieResponse(
    val results: ArrayList<Movie>,
    val totalResults: Int,
    val totalPages: Int,
    val page: Int
) : Serializable