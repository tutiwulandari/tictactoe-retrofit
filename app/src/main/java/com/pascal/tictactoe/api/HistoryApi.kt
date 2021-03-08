package com.pascal.tictactoe.api

import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.models.HistoryWinnerList
import com.pascal.tictactoe.models.ResponseHistory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HistoryApi {

    @GET("/history")
  suspend fun getHistoryWin(): Response<HistoryResponse>

    @POST("/history")
    suspend fun addWinner(@Body request: HistoryRequest) : Response<ResponseHistory>
}