package com.pascal.tictactoe.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.models.HistoryWinner
import com.pascal.tictactoe.repositories.HistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    private var _historyLiveData = MutableLiveData<List<HistoryWinner>>()
    val historyLiveData: LiveData<List<HistoryWinner>>
        get() {
            return _historyLiveData
        }

    fun getHistoryWinner() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getHistoryWin()
            if (response.isSuccessful) {
                response.body().let {
                    _historyLiveData.postValue(it?.data?.historyList)
                }
            }

        }
    }

}