package com.example.gameslibrary.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gameslibrary.ApiKey.SearchGamesResponse
import com.example.gameslibrary.List.ListGamesViewModel
import com.example.gameslibrary.Network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchGamesViewModel: ViewModel() {

    private companion object {
        const val TAG = "SearchGamesViewModel"
        private const val SEARCHGAME_ID = "7a755635e1c8415c9331394ea2d6786a"
    }

    private val _searchGames = MutableLiveData<SearchGamesResponse>()
    val searchGameslibrary: LiveData<SearchGamesResponse> = _searchGames

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun listSearchUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchGames(SEARCHGAME_ID)
        client.enqueue(object : Callback<SearchGamesResponse> {
            override fun onResponse(
                call: Call<SearchGamesResponse>,
                response: Response<SearchGamesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _searchGames.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: Gagal")
                }
            }

            override fun onFailure(call: Call<SearchGamesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}