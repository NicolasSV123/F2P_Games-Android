package com.nicolas.f2p_games.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nicolas.f2p_games.R
import com.nicolas.f2p_games.data.FreeToGameApi
import com.nicolas.f2p_games.data.Game
import com.nicolas.f2p_games.databinding.ActivityDetailBinding
import com.nicolas.f2p_screenshots.adapters.ScreenshotAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_GAME_ID = "GAME_ID"
    }

    lateinit var binding: ActivityDetailBinding

    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_GAME_ID, -1)

        //Obtener el juego de la API con el ID
        val api = FreeToGameApi.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
           game = api.getGameById(id)
            CoroutineScope(Dispatchers.Main).launch {
                loadData()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun loadData(){
        supportActionBar?.title = game.title
        supportActionBar?.subtitle = game.developer
        //supportActionBar?.subtitle = game.

        //Hero.

        Picasso.get().load(game.thumbnail).into(binding.thumbnailImageView);


        binding.titleTextView.text = game.title
        binding.chip.text = game.genre

        val platformResId = if (game.platform.contains("Windows")){
            R.drawable.ic_platform_pc_windows
        } else{
            R.drawable.ic_platform_web
        }


        binding.platformImageView.setImageResource(platformResId)

        //descripcion
        binding.descriptionTextView.text = game.description

        //Screenshots
        val adapter = ScreenshotAdapter(game.screenshots ?: emptyList())
        binding.screenshotsRecyclerView.adapter = adapter

        //requisitos del sistema.

    }
}