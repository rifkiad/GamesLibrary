package com.example.gameslibrary.List

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gameslibrary.ApiKey.SearchGamesResponse
import com.example.gameslibrary.ListGamesResponse
import com.example.gameslibrary.Network.ApiConfig
import com.example.gameslibrary.search.SearchGamesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGamesViewModel : ViewModel() {

    private companion object {
        const val TAG = "ListUserViewModel"
        private const val LISTGAME_ID = "7a755635e1c8415c9331394ea2d6786a"
    }

    private val _listGames = MutableLiveData<ListGamesResponse>()
    val listGames: LiveData<ListGamesResponse> = _listGames

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun listGamesUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListGames(LISTGAME_ID)
        client.enqueue(object : Callback<ListGamesResponse> {
            override fun onResponse(
                call: Call<ListGamesResponse>,
                response: Response<ListGamesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGames.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: Gagal")
                }
            }

            override fun onFailure(call: Call<ListGamesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}