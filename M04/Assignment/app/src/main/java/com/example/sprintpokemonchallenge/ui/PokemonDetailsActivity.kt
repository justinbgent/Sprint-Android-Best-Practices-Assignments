package com.example.sprintpokemonchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sprintpokemonchallenge.R
import com.example.sprintpokemonchallenge.ui.MainActivity.Companion.pokemonInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.*

class PokemonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)


        Picasso.get().load(pokemonInfo.sprites.pokemonSprite).into(image_sprite)
        txt_pokemon_name.text = pokemonInfo.name
        txt_id.text = pokemonInfo.id.toString()

        pokemonInfo.sprites
        pokemonInfo.types

        var abilities = ""
        for (i in pokemonInfo.abilities.indices){
            abilities += "-${pokemonInfo.abilities[i].ability.name} "
        }
        txt_abilities.text = abilities


        var types = ""
        for (i in pokemonInfo.types.indices){
            types += "-${pokemonInfo.types[i].type.name} "
        }
        txt_types.text = types

    }
}
