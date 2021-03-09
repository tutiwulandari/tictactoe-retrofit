package com.pascal.tictactoe.api

import com.pascal.tictactoe.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HistoryApi {

    @GET("/history")
  suspend fun getHistoryWin(): Response<List<HistoryWinner>>

    @POST("/history")
    suspend fun addWinner(@Body request: HistoryRequest) : Response<HistoryWinner>
}