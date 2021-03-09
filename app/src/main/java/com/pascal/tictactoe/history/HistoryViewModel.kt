package com.pascal.tictactoe.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.models.HistoryWinner
import com.pascal.tictactoe.repositories.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {

    @Inject
    @Named("GameRepo")
    lateinit var repository:HistoryRepository


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
                    _historyLiveData.postValue(it)
                }
            }

        }
    }

}