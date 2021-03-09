package com.pascal.tictactoe.repositories

import com.pascal.tictactoe.api.HistoryApi
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryWinner
import retrofit2.Response
import javax.inject.Inject

class HistoryRepositoryImpl
@Inject
constructor(private val historyApi: HistoryApi) : HistoryRepository {

    override suspend fun getHistoryWin(): Response<List<HistoryWinner>> =
       historyApi.getHistoryWin()


    override suspend fun addWinner(request: HistoryRequest): Response<HistoryWinner> =
      historyApi.addWinner(request)

}