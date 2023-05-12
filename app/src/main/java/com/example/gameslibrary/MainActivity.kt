package com.example.gameslibrary

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gameslibrary.ApiKey.SearchGamesResponse
import com.example.gameslibrary.databinding.ActivityMainBinding
import com.example.gameslibrary.search.SearchGamesViewModel
import com.example.gameslibrary.search.SearchModelAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    //private lateinit var viewModel : ListGamesViewModel
    private lateinit var viewModel: SearchGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SearchGamesViewModel::class.java]
        viewModel.searchGameslibrary.observe(this) {
            setListGames(it)
        }
        viewModel.isLoading.observe(this) {
            setLoading(it)
        }
    }

   private fun setListGames(searchGamesResponse: SearchGamesResponse) {
       val adapter = SearchModelAdapter()
       binding.rvListGames.adapter = adapter
       binding.rvListGames.layoutManager = LinearLayoutManager(this)

       adapter.setListItems(searchGamesResponse.results)
   }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}