package com.pascal.tictactoe.api

import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HistoryApi {

    @GET("/history")
   fun getHistoryWin() : Call<List<HistoryResponse>>

    @POST("/history")
    fun addWinner(@Body request: HistoryRequest) :Call<HistoryResponse>
}