package com.app.myapplication.feature.di

import com.app.myapplication.App
import com.app.myapplication.feature.data.local.ConnectDb
import com.app.myapplication.feature.data.remote.Retrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

object DataModule {

    val module = module {
        single {
            Retrofit.create()
        }
        single {
            ConnectDb.getInstance(androidContext())
        }
        single {
            App()
        }
    }
}