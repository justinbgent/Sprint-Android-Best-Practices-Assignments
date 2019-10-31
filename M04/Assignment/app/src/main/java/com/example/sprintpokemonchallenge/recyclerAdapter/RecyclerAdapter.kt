package com.example.sprintpokemonchallenge.recyclerAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintpokemonchallenge.R
import com.example.sprintpokemonchallenge.model.PokemonDetail
import com.example.sprintpokemonchallenge.ui.MainActivity
import com.example.sprintpokemonchallenge.ui.MainActivity.Companion.searchedPokemon
import com.example.sprintpokemonchallenge.ui.PokemonDetailsActivity
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerAdapter(private val pokemonIndex: List<PokemonDetail>?) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pokemonIndex?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (pokemonIndex != null){
            val pokemon = pokemonIndex[position]

            holder.name.text = pokemon.name

            holder.name.setOnClickListener {
                MainActivity.pokemonInfo = pokemon
                val intent = Intent(context, PokemonDetailsActivity::class.java)
                context.startActivity(intent)
            }

            holder.name.setOnLongClickListener{
                searchedPokemon.remove(pokemon)

                this.notifyDataSetChanged()
                return@setOnLongClickListener true
            }
        }



    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.txt_name
    }
}