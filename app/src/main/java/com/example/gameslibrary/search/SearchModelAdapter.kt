package com.example.gameslibrary.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameslibrary.ResultsItem
import com.example.gameslibrary.databinding.ItemGamesBinding
import com.example.gameslibrary.detail.DetailGamesActivity

class SearchModelAdapter : RecyclerView.Adapter<SearchModelAdapter.SearchModelViewHolder>() {

    private var listResult: MutableList<ResultsItem> = mutableListOf()

    fun setListItems(listItem: List<com.example.gameslibrary.ApiKey.ResultsItem?>?) {
        clearListItems()
        addListItems(listItem as List<ResultsItem>)
    }

    private fun clearListItems() {
        this.listResult.clear()
    }

    private fun addListItems(listItem: List<ResultsItem>) {
        this.listResult.addAll(listItem)
    }

    class SearchModelViewHolder(private val binding: ItemGamesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: ResultsItem) {
            with(item) {
                binding.tvItemName.text = this.name
                binding.tvItemRilis.text = this.released
                Glide.with(itemView)
                    .load(this.backgroundImage)
                    .into(binding.imageView)

                itemView.setOnClickListener {
                    //Toast.makeText(it.context, "Detail", Toast.LENGTH_SHORT).show()
                    val intentToDetail = Intent(it.context, DetailGamesActivity::class.java)
                    intentToDetail.putExtra("USERNAME", this.name)
                    it.context.startActivity(intentToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchModelViewHolder {
        val binding = ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchModelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    override fun onBindViewHolder(holder: SearchModelViewHolder, position: Int) {
        holder.bindView(listResult[position])
    }
}