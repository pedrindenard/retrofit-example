package com.app.myapplication.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.myapplication.feature.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "count_vote") val countVote: Int = 0,
    @ColumnInfo(name = "average_vote") val averageVote: Float = 0F,
    @ColumnInfo(name = "popularity") val popularity: Float = 0F,

    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "img_background") val imgBackground: String = "",
    @ColumnInfo(name = "img_foreground") val imgForeground: String = "",
    @ColumnInfo(name = "original_title") val originalTitle: String = "",
    @ColumnInfo(name = "original_language") val originalLanguage: String = "",
    @ColumnInfo(name = "overview") val overview: String = "",
    @ColumnInfo(name = "release_date") val releaseDate: String = "",

    @ColumnInfo(name = "video_available") val videoAvailable: Boolean = false,
    @ColumnInfo(name = "is_to_adult") val isToAdult: Boolean = false
) {
    val movie: Movie
        get() = Movie(
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