package com.nicolas.f2p_games.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicolas.f2p_games.R
import com.nicolas.f2p_games.adapters.GameAdapter
import com.nicolas.f2p_games.data.FreeToGameApi
import com.nicolas.f2p_games.data.Game
import com.nicolas.f2p_games.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: GameAdapter

    var gameList: List<Game> = emptyList()
    var filteredGameList: List<Game> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //COnfiguramos el Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Configuracion ReclyclerView
        adapter = GameAdapter(gameList){ position ->
            val game = filteredGameList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_GAME_ID, game.id)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //Obtener los juegos de la API
        val api = FreeToGameApi.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            gameList = api.getGamesList()
            filteredGameList = gameList

            CoroutineScope(Dispatchers.Main).launch {
                adapter.updateData(gameList)
                showData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val searchMenuItem = menu.findItem(R.id.menu_search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filteredGameList = gameList.filter {
                    it.title.contains(newText, true)
                }
                showData()
                return true
            }
        })

        return true
    }

    fun showData() {
        adapter.updateData(filteredGameList)
        supportActionBar?.subtitle = "Found ${filteredGameList.size} games"
    }
}