package com.app.myapplication.core

sealed class Resource<out T> {

    data class Success<out T>(
        val data: T
    ) : Resource<T>()

    data class Failure<out T>(
        val data: T? = null,
        val throwable: Throwable,
        val message: UIText.StringResource
    ) : Resource<T>()

    data class Error<out T>(
        val data: T? = null,
        val message: UIText.StringResource
    ) : Resource<T>()

    data class InvalidToken<out T>(
        val data: T? = null,
        val message: UIText.StringResource
    ) : Resource<T>()
}