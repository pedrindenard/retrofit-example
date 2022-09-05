package com.app.myapplication.feature.data.data_source

import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.data.remote.network.Api
import com.app.myapplication.feature.domain.data_source.DataSource
import com.app.myapplication.feature.domain.model.MovieDetails
import com.app.myapplication.feature.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataSourceImpl(private val api: Api) : DataSource {

    override suspend fun getPopularMovie(): Flow<Resource<MovieResponse>> =
        flow {
            val response = api.getPopularMovie()

            when (response.code()) {
                in 200..300 -> {
                    emit(Resource.Success(response.body()!!.result))
                }
                400, 401 -> {
                    emit(
                        Resource.InvalidToken(
                            response.body()?.result,
                            UIText.StringResource(R.string.message_invalid_api_key)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            response.body()?.result,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> =
        flow {
            val response = api.getMovieDetails(movieId)

            when (response.code()) {
                in 200..300 -> {
                    emit(Resource.Success(response.body()!!.movie))
                }
                400, 401 -> {
                    emit(
                        Resource.InvalidToken(
                            response.body()?.movie,
                            UIText.StringResource(R.string.message_invalid_api_key)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            response.body()?.movie,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }

    override suspend fun getSearchMovie(query: String): Flow<Resource<MovieResponse>> =
        flow {
            val response = api.getSearchMovie(query)

            when (response.code()) {
                in 200..300 -> {
                    emit(Resource.Success(response.body()!!.result))
                }
                400, 401 -> {
                    emit(
                        Resource.InvalidToken(
                            response.body()?.result,
                            UIText.StringResource(R.string.message_invalid_api_key)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            response.body()?.result,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }
}