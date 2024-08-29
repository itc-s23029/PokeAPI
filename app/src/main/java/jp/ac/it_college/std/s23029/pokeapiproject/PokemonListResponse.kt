package jp.ac.it_college.std.s23029.pokeapiproject

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)
