package com.pascal.tictactoe.api

import com.pascal.tictactoe.utils.Constans
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val historyApi: HistoryApi by lazy {
        retrofit.create(HistoryApi::class.java)
    }
}