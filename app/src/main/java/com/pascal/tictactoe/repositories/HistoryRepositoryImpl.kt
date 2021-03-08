package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.api.RetrofitInstance
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.models.ResponseHistory
import retrofit2.Response

class HistoryRepositoryImpl : HistoryRepository {
    override suspend fun getHistoryWin(): Response<HistoryResponse> =
        RetrofitInstance.historyApi.getHistoryWin()


    override suspend fun addWinner(request: HistoryRequest): Response<ResponseHistory> =
        RetrofitInstance.historyApi.addWinner(request)

}