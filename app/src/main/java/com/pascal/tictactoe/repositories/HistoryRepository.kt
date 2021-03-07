package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import retrofit2.Response

interface HistoryRepository {

    suspend fun getHistoryWin() : Response<List<HistoryResponse>>
    suspend fun addWinner(request:HistoryRequest) :Response<HistoryResponse>

}