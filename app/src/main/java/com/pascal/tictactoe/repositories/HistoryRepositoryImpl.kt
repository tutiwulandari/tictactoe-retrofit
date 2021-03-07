package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.api.RetrofitInstance
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import retrofit2.Response

class HistoryRepositoryImpl : HistoryRepository {
    override suspend fun getHistoryWin(): Response<List<HistoryResponse>> =
        RetrofitInstance.service().getHistoryWin()

    override suspend fun addWinner(request: HistoryRequest): Response<HistoryResponse> =
      RetrofitInstance.service().addWinner(request)

}