package com.app.myapplication.feature.di

import com.app.myapplication.feature.presentation.fragment.details.DetailsViewModel
import com.app.myapplication.feature.presentation.fragment.favorite.FavoriteViewModel
import com.app.myapplication.feature.presentation.fragment.home.HomeViewModel
import com.app.myapplication.feature.presentation.fragment.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val module = module(override = true) {
        viewModel {
            DetailsViewModel(useCase = get())
        }
        viewModel {
            HomeViewModel(useCase = get())
        }
        viewModel {
            SearchViewModel(useCase = get())
        }
        viewModel {
            FavoriteViewModel(useCase = get())
        }
    }
}