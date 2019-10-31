package com.example.sprintpokemonchallenge.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(val abilities: List<Ability>, val id: Int, val name: String, val sprites: Sprites, val types: List<Types>)
//Next five data class's help flesh out the above class, See parameters
data class Ability(val ability: AbilityDetails)
    //Below A
data class AbilityDetails(val name: String/*, val url: String*/)

data class Sprites(@SerializedName("front_default")val pokemonSprite: String)

data class Types(val type: Type)

data class Type(val name: String)
//"front_default"