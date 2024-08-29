package jp.ac.it_college.std.s23029.pokeapiproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s23029.pokeapiproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = PokemonListAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val apiService = RetrofitClient.apiService
        val call = apiService.getPokemonList(30, 0)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()?.results ?: emptyList()
                    Log.d("MainActivity", "Pokemon list: $pokemonList")
                    adapter.updateData(pokemonList)
                } else {
                    Log.d("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Log.d("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}