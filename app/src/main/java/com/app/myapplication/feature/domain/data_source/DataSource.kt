package com.app.myapplication.feature.domain.data_source

import com.app.myapplication.core.Resource
import com.app.myapplication.feature.domain.model.MovieDetails
import com.app.myapplication.feature.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {

    suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>

    suspend fun getSearchMovie(query: String): Flow<Resource<MovieResponse>>

    suspend fun getPopularMovie(): Flow<Resource<MovieResponse>>
}