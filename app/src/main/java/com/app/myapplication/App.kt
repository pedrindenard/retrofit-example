package com.app.myapplication

import android.app.Application
import com.app.myapplication.feature.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setKoin()
    }

    private fun setKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)

            koin.loadModules(
                listOf(
                    DataModule.module,
                    DataSourceModule.module,
                    RepositoryModule.module,
                    UseCaseModule.module,
                    ViewModelModule.module
                )
            )
        }
    }
}