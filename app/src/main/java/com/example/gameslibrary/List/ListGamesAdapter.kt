package com.example.gameslibrary.List

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameslibrary.ResultsItem
import com.example.gameslibrary.databinding.ItemGamesBinding

class ListGamesAdapter : RecyclerView.Adapter<ListGamesAdapter.ListGamesViewHolder>() {

    private var listItem: MutableList<ResultsItem> = mutableListOf()

    fun setListItems(listItem: List<ResultsItem?>?) {
        clearListItems()
        addListItems(listItem as List<ResultsItem>)
    }

    private fun clearListItems() {
        this.listItem.clear()
    }

    private fun addListItems(listItem: List<ResultsItem>) {
        this.listItem.addAll(listItem)
    }

    class ListGamesViewHolder(private val binding: ItemGamesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: ResultsItem) {
            with(item) {
                binding.tvItemName.text = this.name
                binding.tvItemRilis.text = this.released
                Glide.with(itemView)
                    .load(this.backgroundImage)
                    .into(binding.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGamesViewHolder {
        val binding = ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListGamesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ListGamesViewHolder, position: Int) {
        holder.bindView(listItem[position])
    }
}