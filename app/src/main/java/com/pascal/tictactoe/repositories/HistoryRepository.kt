package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryWinner
import retrofit2.Response

interface HistoryRepository {

    suspend fun getHistoryWin() : Response<List<HistoryWinner>>
    suspend fun addWinner(request:HistoryRequest) :Response<HistoryWinner>

}