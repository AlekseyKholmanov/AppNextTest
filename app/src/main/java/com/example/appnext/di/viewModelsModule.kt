package com.example.appnext.di

import com.example.appnext.ui.viewModels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel(
            weeklyActivityInfoUseCase = get()
        )
    }
}