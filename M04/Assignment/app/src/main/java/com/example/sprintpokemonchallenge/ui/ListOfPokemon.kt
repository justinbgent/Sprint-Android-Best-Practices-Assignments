package com.example.sprintpokemonchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sprintpokemonchallenge.R

class ListOfPokemon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_pokemon)

//        recycler.setHasFixedSize(true)
//        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        val adapter = RecyclerAdapter(pokemonList)
//        recycler.layoutManager = manager
//        recycler.adapter = adapter
    }
}
