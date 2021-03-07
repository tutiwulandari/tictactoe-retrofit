package com.pascal.tictactoe.api

import com.pascal.tictactoe.utils.Constans
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder().baseUrl(Constans.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun service() = retrofit.create(HistoryApi::class.java)
}