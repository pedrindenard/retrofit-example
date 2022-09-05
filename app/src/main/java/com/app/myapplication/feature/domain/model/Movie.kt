package com.app.myapplication.feature.domain.model

import android.content.Context
import com.app.myapplication.R
import com.app.myapplication.feature.data.local.entity.MovieEntity
import java.io.Serializable

data class Movie(
    val id: Int,
    val countVote: Int,
    val averageVote: Float,
    val popularity: Float,
    val title: String,
    val imgBackground: String,
    val imgForeground: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val videoAvailable: Boolean,
    val isToAdult: Boolean
) : Serializable {

    fun overview(context: Context?) =
        overview.ifEmpty { context?.getString(R.string.details_no_overview) }

    fun averageVoteCount(context: Context?) =
        "$countVote ${context?.getString(R.string.details_votes)}"

    val movieEntity: MovieEntity
        get() = MovieEntity(
            id,
            countVote,
            averageVote,
            popularity,
            title,
            imgBackground,
            imgForeground,
            originalTitle,
            originalLanguage,
            overview,
            releaseDate,
            videoAvailable,
            isToAdult
        )
}