package com.app.myapplication.feature.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.myapplication.feature.data.local.dao.MovieDao
import com.app.myapplication.feature.data.local.entity.MovieEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [MovieEntity::class]
)

abstract class ConnectDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: ConnectDb? = null

        private const val database = "database-my-application.db"

        fun getInstance(context: Context): ConnectDb {
            return if (INSTANCE != null) { INSTANCE!! } else {
                synchronized(lock = this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConnectDb::class.java,
                        database
                    ).build()

                    INSTANCE = instance

                    return instance
                }
            }
        }
    }
}