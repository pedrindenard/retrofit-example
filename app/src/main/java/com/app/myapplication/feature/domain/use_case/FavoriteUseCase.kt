package com.app.myapplication.feature.domain.use_case

import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.feature.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FavoriteUseCase(private val repository: Repository) {

    suspend operator fun invoke(): Flow<Resource<List<Movie>>> {
        return repository.getFavoriteMovies().flowOn(Dispatchers.IO).map {
            if (it is Resource.Success && it.data.isEmpty()) {
                Resource.Error(null, UIText.StringResource(R.string.message_invalid_error))
            } else {
                it
            }
        }
    }
}