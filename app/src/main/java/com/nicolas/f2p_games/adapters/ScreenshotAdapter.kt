package com.nicolas.f2p_screenshots.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.f2p_games.data.Screenshot
import com.nicolas.f2p_games.databinding.ItemScreenshotBinding
import com.squareup.picasso.Picasso

class ScreenshotAdapter(
    var items: List<Screenshot>,
) : RecyclerView.Adapter<ScreenshotViewHolder>() {
    override fun onCreateViewHolder( parent: ViewGroup,  viewType: Int ): ScreenshotViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemScreenshotBinding.inflate(layoutInflater, parent, false)
        return ScreenshotViewHolder(binding)
    }

    override fun onBindViewHolder( holder: ScreenshotViewHolder,  position: Int ) {
        val screenshot = items[position]
        holder.render(screenshot)

    }

    override fun getItemCount(): Int = items.size


    fun updateData(dataSet: List<Screenshot>){
        items = dataSet
        notifyDataSetChanged()
    }
}

class ScreenshotViewHolder(val binding: ItemScreenshotBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(screenshot: Screenshot) {

    // binding.thumbnailImageView
    }
}