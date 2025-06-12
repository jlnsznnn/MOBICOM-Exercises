// Holds references to the views in each item to avoid repeated view lookups
package com.example.fakeadex

import PokemonModel
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fakeadex.databinding.ItemPokemonLayoutBinding

class MyViewHolder(private val viewBinding: ItemPokemonLayoutBinding): RecyclerView.ViewHolder(viewBinding.root){
    fun bindData(model: PokemonModel){
        this.viewBinding.pokemonNameTv.text = model.name
        this.viewBinding.pokemonDescTv.text = model.desc
        this.viewBinding.pokemonSpeciesTv.text = model.specie
        this.viewBinding.pokemonLocationTv.text = model.location
        this.viewBinding.pokemonImg.setImageResource(model.imageId)
    }

    fun setDeleteOnClickListener(onClickListener: View.OnClickListener){
        this.viewBinding.deleteBtn.setOnClickListener(onClickListener)
    }
}

