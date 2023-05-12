package com.example.gameslibrary.Network

import com.example.gameslibrary.ApiKey.SearchGamesResponse
import com.example.gameslibrary.ListGamesResponse
import com.example.gameslibrary.detail.DetailGamesActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("/api/games?")
    @Headers("Authorization: token 7a755635e1c8415c9331394ea2d6786a")
    fun getSearchGames(
        @Query("q") q: String
    ): Call<SearchGamesResponse>

    @GET("/api/games?")
    @Headers("Authorization: token 7a755635e1c8415c9331394ea2d6786a")
    fun getListGames(
        @Query("q") q: String
    ): Call<ListGamesResponse>

//    @GET("/api/games?")
//    @Headers("Authorization: token 7a755635e1c8415c9331394ea2d6786a")
//    fun getDetailGames(
//        @Query("q") q: String
//    ): Call<>


}