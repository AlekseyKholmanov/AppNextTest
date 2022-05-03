package com.example.appnext.di

import com.example.appnext.workers.InititalWorker
import com.example.appnext.workers.PeriodicWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workerModule = module {
    worker {
        InititalWorker(get(), get(), get(), get())
    }
    worker {
        PeriodicWorker(get(), get(), get(), get())
    }
}