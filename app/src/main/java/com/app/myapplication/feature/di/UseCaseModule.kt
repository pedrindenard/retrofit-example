package com.app.myapplication.feature.di

import com.app.myapplication.feature.domain.use_case.DetailsUseCase
import com.app.myapplication.feature.domain.use_case.FavoriteUseCase
import com.app.myapplication.feature.domain.use_case.MoviesUseCase
import com.app.myapplication.feature.domain.use_case.SearchUseCase
import org.koin.dsl.module

object UseCaseModule {

    val module = module(override = true) {
        single {
            MoviesUseCase(repository = get())
        }
        single {
            SearchUseCase(repository = get())
        }
        single {
            FavoriteUseCase(repository = get())
        }
        single {
            DetailsUseCase(repository = get())
        }
    }
}