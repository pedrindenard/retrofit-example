package com.app.myapplication.feature.di

import com.app.myapplication.feature.data.data_source.DataSourceImpl
import com.app.myapplication.feature.domain.data_source.DataSource
import org.koin.dsl.module

object DataSourceModule {

    val module = module(override = true) {
        single<DataSource> {
            DataSourceImpl(api = get())
        }
    }
}