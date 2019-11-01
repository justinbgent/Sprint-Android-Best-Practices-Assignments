package com.example.sprintpokemonchallenge.retrofit

import dagger.Module
import dagger.Provides

@Module
object Module {

    @Provides
    @JvmStatic
    fun provideRetroInstance(): RetrofitInstance{
        return RetrofitInstance()
    }
}