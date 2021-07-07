package com.example.sprintpokemonchallenge.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintpokemonchallenge.R
import com.example.sprintpokemonchallenge.model.PokemonDetail
import com.example.sprintpokemonchallenge.recyclerAdapter.RecyclerAdapter
import com.example.sprintpokemonchallenge.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var pokemonInfo: PokemonDetail
        var searchedPokemon: ArrayList<PokemonDetail> = arrayListOf()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            RetrofitInstance.getPokemon(edit_txt.text.toString().toLowerCase()).enqueue(object: Callback<PokemonDetail>{
                override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                }

                override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                    if (response.body() != null){
                        pokemonInfo = response.body() as PokemonDetail

                        var addPokemonToList = true
                        if (searchedPokemon.size < 1){
                            searchedPokemon.add(pokemonInfo)
                            addPokemonToList = false
                        }
                        else {
                            for (i in searchedPokemon.indices){
                                if(searchedPokemon[i] == pokemonInfo){
                                    addPokemonToList = false
                                    break
                                }
                            }
                        }
                        if (addPokemonToList){
                            searchedPokemon.add(pokemonInfo)
                        }

                        val intent = Intent(this@MainActivity, PokemonDetailsActivity::class.java)
                        startActivity(intent)
                    }
                }
            })

        }

    }

    override fun onResume() {
        recycler.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.layoutManager = manager
        val adapter = RecyclerAdapter(searchedPokemon)
        recycler.adapter = adapter
        super.onResume()

//        edit_txt.setText()
    }
}
