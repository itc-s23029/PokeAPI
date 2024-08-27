package jp.ac.it_college.std.s23029.pokeapiproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import jp.ac.it_college.std.s23029.pokeapiproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        val apiService = RetrofitClient.apiService
        val call = apiService.getPokemon("pikachu")

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    binding.textView.text = "Pokemon: ${pokemon?.name}, ID: ${pokemon?.id}"
                    Log.d("MainActivity", "Pokemon: $pokemon")
                } else {
                    Log.d("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}