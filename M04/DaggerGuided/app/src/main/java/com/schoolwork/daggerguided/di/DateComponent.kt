package com.schoolwork.daggerguided.di

import com.schoolwork.daggerguided.di.DaggerActivity
import com.schoolwork.daggerguided.di.DateModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DateModule::class])
interface DateComponent {

    fun inject(daggerActivity: DaggerActivity)
}