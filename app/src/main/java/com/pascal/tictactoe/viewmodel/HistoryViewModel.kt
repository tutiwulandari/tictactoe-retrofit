package com.pascal.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.repositories.HistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    private var _historyWinner = MutableLiveData<List<HistoryResponse>>()
    val historyWinner: LiveData<List<HistoryResponse>>
        get() {
            return _historyWinner
        }

    fun getHistoryWinner() {
       CoroutineScope(Dispatchers.IO).launch {
           val response = repository.getHistoryWin()
           if(response.isSuccessful) {
               response.body().let {
                _historyWinner.postValue(it)
               }
           }
            else {
                _historyWinner.postValue(null)
           }

           }
       }

}