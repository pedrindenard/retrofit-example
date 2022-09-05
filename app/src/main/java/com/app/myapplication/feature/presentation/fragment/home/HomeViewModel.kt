package com.app.myapplication.feature.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myapplication.core.Resource
import com.app.myapplication.feature.domain.model.MovieResponse
import com.app.myapplication.feature.domain.use_case.MoviesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: MoviesUseCase) : ViewModel() {

    private val _uiResponseEvent = MutableLiveData<Resource<MovieResponse>>()
    val uiResponseEvent: LiveData<Resource<MovieResponse>>
        get() = _uiResponseEvent

    private val _uiLoadingEvent = MutableLiveData<Boolean>()
    val uiLoadingEvent: LiveData<Boolean>
        get() = _uiLoadingEvent

    fun getPopularMovies() = viewModelScope.launch {
        useCase.invoke().onStart {
            _uiLoadingEvent.postValue(true)
        }.onCompletion {
            _uiLoadingEvent.postValue(false)
        }.onEach { result ->
            when (result) {
                is Resource.Success -> _uiResponseEvent.postValue(
                    Resource.Success(result.data)
                )
                is Resource.Error -> _uiResponseEvent.postValue(
                    Resource.Error(result.data, result.message)
                )
                is Resource.Failure -> _uiResponseEvent.postValue(
                    Resource.Failure(result.data, result.throwable, result.message)
                )
                is Resource.InvalidToken -> _uiResponseEvent.postValue(
                    Resource.InvalidToken(result.data, result.message)
                )
            }
        }.launchIn(this)
    }
}