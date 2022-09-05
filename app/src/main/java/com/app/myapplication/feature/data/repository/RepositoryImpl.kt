package com.app.myapplication.feature.data.repository

import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.data.local.ConnectDb
import com.app.myapplication.feature.domain.data_source.DataSource
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.feature.domain.model.MovieDetails
import com.app.myapplication.feature.domain.model.MovieResponse
import com.app.myapplication.feature.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RepositoryImpl(
    private val dataSource: DataSource,
    private val dao: ConnectDb
) : Repository {

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> =
        dataSource.getMovieDetails(movieId).catch {
            when (it) {
                is HttpException -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
                is IOException -> {
                    emit(
                        Resource.Failure(
                            null,
                            it,
                            UIText.StringResource(R.string.message_invalid_connection)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }

    override suspend fun getSearchMovie(query: String): Flow<Resource<MovieResponse>> =
        dataSource.getSearchMovie(query).catch {
            when (it) {
                is HttpException -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
                is IOException -> {
                    emit(
                        Resource.Failure(
                            null,
                            it,
                            UIText.StringResource(R.string.message_invalid_connection)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }

    override suspend fun getPopularMovie(): Flow<Resource<MovieResponse>> =
        dataSource.getPopularMovie().catch {
            when (it) {
                is HttpException -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
                is IOException -> {
                    emit(
                        Resource.Failure(
                            null,
                            it,
                            UIText.StringResource(R.string.message_invalid_connection)
                        )
                    )
                }
                else -> {
                    emit(
                        Resource.Error(
                            null,
                            UIText.StringResource(R.string.message_invalid_error)
                        )
                    )
                }
            }
        }

    override suspend fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        val message = UIText.StringResource(R.string.message_invalid_error)
        val result = dao.movieDao().select()?.map { it.movie }

        if (!result.isNullOrEmpty()) {
            emit(Resource.Success(result))
        } else {
            emit(Resource.Error(result, message))
        }
    }
}