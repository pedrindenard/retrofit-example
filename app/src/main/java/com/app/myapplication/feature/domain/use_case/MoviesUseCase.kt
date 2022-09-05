package com.app.myapplication.feature.domain.use_case

import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.domain.model.MovieResponse
import com.app.myapplication.feature.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MoviesUseCase(private val repository: Repository) {

    suspend operator fun invoke(): Flow<Resource<MovieResponse>> {
        return repository.getPopularMovie().flowOn(Dispatchers.IO).map {
            if (it is Resource.Success && it.data.results.isEmpty()) {
                Resource.Error(null, UIText.StringResource(R.string.message_invalid_error))
            } else {
                it
            }
        }
    }
}