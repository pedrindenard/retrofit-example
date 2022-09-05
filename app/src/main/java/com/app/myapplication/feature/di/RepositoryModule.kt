package com.app.myapplication.feature.di

import com.app.myapplication.feature.data.repository.RepositoryImpl
import com.app.myapplication.feature.domain.repository.Repository
import org.koin.dsl.module

object RepositoryModule {

    val module = module(override = true) {
        single<Repository> {
            RepositoryImpl(dataSource = get(), dao = get())
        }
    }
}