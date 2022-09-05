package com.app.myapplication.feature.data.local.dao

import androidx.room.*
import com.app.myapplication.feature.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query(value = "SELECT * FROM movie")
    suspend fun select(): List<MovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg movie: MovieEntity)

    @Query(value = "DELETE FROM movie WHERE id = :id")
    fun delete(id: Int)
}