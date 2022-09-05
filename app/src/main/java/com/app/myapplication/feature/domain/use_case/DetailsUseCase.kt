package com.app.myapplication.feature.domain.use_case

import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.domain.model.MovieDetails
import com.app.myapplication.feature.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DetailsUseCase(private val repository: Repository) {

    suspend operator fun invoke(movieId: String): Flow<Resource<MovieDetails>> {
        return repository.getMovieDetails(movieId).flowOn(Dispatchers.IO).map {
            if (it is Resource.Success && it.data.id == 0 && it.data.originalTitle == "" && it.data.title == "") {
                Resource.Error(null, UIText.StringResource(R.string.message_invalid_error))
            } else {
                it
            }
        }
    }
}