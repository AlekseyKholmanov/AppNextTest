package com.example.appnext.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val toolsModule = module {
    single {
        androidApplication().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}