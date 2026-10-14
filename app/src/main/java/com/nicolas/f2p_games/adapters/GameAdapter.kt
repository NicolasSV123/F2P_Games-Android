package com.nicolas.f2p_games.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.f2p_games.R
import com.nicolas.f2p_games.data.Game
import com.nicolas.f2p_games.databinding.ItemGameBinding
import com.squareup.picasso.Picasso

class GameAdapter(
    var items: List<Game>,
    val onItemClick:(position: Int) -> Unit
) : RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup,  viewType: Int ): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGameBinding.inflate(layoutInflater, parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder( holder: GameViewHolder,  position: Int ) {
        val game = items[position]
        holder.render(game)
        holder.binding.gameCard.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = items.size


    fun updateData(dataSet: List<Game>){
        items = dataSet
        notifyDataSetChanged()
    }
}

class GameViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(game: Game) {
        binding.titleTextView.text = game.title
        binding.descriptionTextView.text = game.shortDescription
        binding.chip.text = game.genre

        val platformResId = if (game.platform.contains("Windows")){
            R.drawable.ic_platform_pc_windows
        } else{
            R.drawable.ic_platform_web
        }

        binding.platformImageView.setImageResource(platformResId)


        Picasso.get().load(game.thumbnail).into(binding.thumbnailImageView);
       // binding.thumbnailImageView
    }
}