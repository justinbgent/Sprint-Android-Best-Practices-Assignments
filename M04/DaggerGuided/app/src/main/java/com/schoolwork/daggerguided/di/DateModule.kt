package com.schoolwork.daggerguided.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton //could be called an object instead of class to accomplish same thing
class DateModule {
    @Provides
    fun providesDateExample(): DateExampleContract {
        return DateExampleImplementation()
    }
}