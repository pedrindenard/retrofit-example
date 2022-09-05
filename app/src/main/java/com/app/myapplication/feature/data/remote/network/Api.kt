package com.app.myapplication.feature.data.remote.network

import com.app.myapplication.feature.data.remote.dto.MovieDetailsDto
import com.app.myapplication.feature.data.remote.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = "movie/popular")
    suspend fun getPopularMovie(): Response<MovieResponseDto>

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = "search/movie")
    suspend fun getSearchMovie(@Query(value = "query") query: String): Response<MovieResponseDto>

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetails(@Path(value = "movie_id") id: String): Response<MovieDetailsDto>
}