package com.schoolwork.daggerguided

import android.app.Application
import com.schoolwork.daggerguided.di.DateComponent
import com.schoolwork.daggerguided.di.DateModule

class DaggerDemoApplication: Application() {

    lateinit var dateComponent: DateComponent
    override fun onCreate() {
        super.onCreate()

        dateComponent = DaggerDateComponent.builder()
            .dateModule(DateModule)
            .build()
    }
}