package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.models.HistoryResponse
import retrofit2.Response

interface HistoryRepository {

    fun getHistoryWin(player1: String, player2:String, winName: String) : Response<HistoryResponse>
}