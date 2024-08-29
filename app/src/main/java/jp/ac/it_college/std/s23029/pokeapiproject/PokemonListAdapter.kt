package jp.ac.it_college.std.s23029.pokeapiproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s23029.pokeapiproject.databinding.ItemPokemonBinding

class PokemonListAdapter (private var pokemonList: List<PokemonResult>) :
        RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int
    ) {
        Log.d("PokemonListAdapter", "Binding Pokemon: ${pokemonList[position].name}")
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    fun updateData(newPokemonList: List<PokemonResult>) {
        pokemonList = newPokemonList
        Log.d("PokemonListAdapter", "Updated Pokemon List: $pokemonList")
        notifyDataSetChanged()
    }

    class PokemonViewHolder(private val binding: ItemPokemonBinding) :
            RecyclerView.ViewHolder(binding.root) {

                fun bind(pokemon: PokemonResult) {
                    val capitalizePokemonName = pokemon.name.substring(0, 1).uppercase() + pokemon.name.substring(1).lowercase()
                    Log.d("PokemonViewHolder", "String text: ${pokemon.name}")
                    binding.textView.text = pokemon.name
                }
            }
        }