package com.example.sprintpokemonchallenge.retrofit

import com.example.sprintpokemonchallenge.model.PokemonDetail
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

//@Module
class RetrofitInstance @Inject constructor(){


    private val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

//    @Singleton
//    @Provides
    fun getPokemon(pokemonNameOrId: String): Call<PokemonDetail>{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(PokemonApi::class.java).getPokemon(pokemonNameOrId)
    }
}