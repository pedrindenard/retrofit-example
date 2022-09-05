package com.app.myapplication.feature.presentation.fragment.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.core.UIText
import com.app.myapplication.feature.data.local.ConnectDb
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.feature.domain.use_case.FavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(private val useCase: FavoriteUseCase) : ViewModel() {

    private val _uiResponseEvent = MutableLiveData<Resource<List<Movie>>>()
    val uiResponseEvent: LiveData<Resource<List<Movie>>>
        get() = _uiResponseEvent

    private val _uiFavoriteEvent = MutableLiveData<Resource<Pair<Int, UIText.StringResource>>>()
    val uiFavoriteEvent: LiveData<Resource<Pair<Int, UIText.StringResource>>>
        get() = _uiFavoriteEvent

    private val _uiLoadingEvent = MutableLiveData<Boolean>()
    val uiLoadingEvent: LiveData<Boolean>
        get() = _uiLoadingEvent

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
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

    fun removeMovieFromFavorite(id: Int, position: Int, context: Context) = viewModelScope.launch(Dispatchers.IO) {
        try {
            ConnectDb.getInstance(context).movieDao().delete(id)
            _uiFavoriteEvent.postValue(
                Resource.Success(
                    Pair(position, UIText.StringResource(R.string.message_success_removed))
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            _uiFavoriteEvent.postValue(
                Resource.Success(
                    Pair(position ,UIText.StringResource(R.string.message_invalid_error))
                )
            )
        }
    }
}