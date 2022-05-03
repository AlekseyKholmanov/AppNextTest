package com.example.appnext.di

import com.example.appnext.useCases.FetchWeeklyInfoUseCase
import com.example.appnext.useCases.FetchWeeklyInfoUseCaseImpl
import com.example.appnext.useCases.WeeklyActivityInfoUseCase
import com.example.appnext.useCases.WeeklyActivityInfoUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {

    single<FetchWeeklyInfoUseCase> { FetchWeeklyInfoUseCaseImpl(api = get()) }

    single<WeeklyActivityInfoUseCase> {
        WeeklyActivityInfoUseCaseImpl(
            db = get(),
            fetchWeeklyInfoUseCase = get()
        )
    }
}