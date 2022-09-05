package com.app.myapplication.feature.domain.repository

import com.app.myapplication.core.Resource
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.feature.domain.model.MovieDetails
import com.app.myapplication.feature.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>

    suspend fun getSearchMovie(query: String): Flow<Resource<MovieResponse>>

    suspend fun getPopularMovie(): Flow<Resource<MovieResponse>>

    suspend fun getFavoriteMovies(): Flow<Resource<List<Movie>>>
}