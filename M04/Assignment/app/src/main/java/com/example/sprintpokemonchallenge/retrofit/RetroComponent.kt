package com.example.sprintpokemonchallenge.retrofit

import dagger.Component

@Component
interface RetroComponent {
    fun getRetroInstance(): RetrofitInstance
}