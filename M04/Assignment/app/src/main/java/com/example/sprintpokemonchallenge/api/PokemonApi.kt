package com.example.sprintpokemonchallenge.api

import com.example.sprintpokemonchallenge.model.PokemonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi{

    @GET("{IdOrName}")
    fun getPokemon(@Path("IdOrName")idOrName: String): Call<PokemonDetail>

//    @GET("{name}")
//    fun getPokemon(@Path("name")pokemonName: String): Call<PokemonDetail>
}