 package com.pascal.tictactoe.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.repositories.HistoryRepository
import com.pascal.tictactoe.utils.ResourceState
import kotlinx.coroutines.*

class BoardViewModel(private val repository: HistoryRepository) : ViewModel() {
    private var _addWinner = MutableLiveData<ResourceState>()
    val addWinner : LiveData<ResourceState>
    get() {
        return _addWinner
    }

    var winner = -1
    var checkWinnerPlayer1 = ArrayList<Int>()
    var checkWinnerPlayer2 = ArrayList<Int>()

//    private var _validationPLayer = MutableLiveData<ResourceState>()
//    val validationPlayer: LiveData<ResourceState>
//        get() {
//            return _validationPLayer
//        }

//    fun inputValidationPlayer(player1: String, player2: String) {
//        GlobalScope.launch {
//            _validationPLayer.postValue(ResourceState.loading())
//            delay(2000)
//            if (player1.isNullOrBlank() || player2.isNullOrBlank()) {
//                _validationPLayer.postValue(ResourceState.failed("Name Player Must Not Blank or Empty"))
//            } else {
//                _validationPLayer.postValue(ResourceState.success(true))
//            }
//        }
//    }

    fun checkWinnerViewModel() {
        //Check Row 1
        if (checkWinnerPlayer1.contains(1) && checkWinnerPlayer1.contains(2) && checkWinnerPlayer1.contains(3)) {
            winner = 1
        }
        //Check Row 1
        if (checkWinnerPlayer2.contains(1) && checkWinnerPlayer2.contains(2) && checkWinnerPlayer2.contains(3)) {
            winner = 2
        }
        //Check Row 2
        if (checkWinnerPlayer1.contains(4) && checkWinnerPlayer1.contains(5) && checkWinnerPlayer1.contains(6)) {
            winner = 1
        }
        //Check Row 2
        if (checkWinnerPlayer2.contains(4) && checkWinnerPlayer2.contains(5) && checkWinnerPlayer2.contains(6)) {
            winner = 2
        }
        //Check Row 3
        if (checkWinnerPlayer1.contains(7) && checkWinnerPlayer1.contains(8) && checkWinnerPlayer1.contains(9)) {
            winner = 1
        }
        //Check Row 3
        if (checkWinnerPlayer2.contains(7) && checkWinnerPlayer2.contains(8) && checkWinnerPlayer2.contains(9)) {
            winner = 2
        }
        //Check Col 1
        if (checkWinnerPlayer1.contains(1) && checkWinnerPlayer1.contains(4) && checkWinnerPlayer1.contains(7)) {
            winner = 1
        }
        //Check Col 1
        if (checkWinnerPlayer2.contains(1) && checkWinnerPlayer2.contains(4) && checkWinnerPlayer2.contains(7)) {
            winner = 2
        }
        //Check Col 2
        if (checkWinnerPlayer1.contains(2) && checkWinnerPlayer1.contains(5) && checkWinnerPlayer1.contains(8)) {
            winner = 1
        }
        //Check Col 2
        if (checkWinnerPlayer2.contains(2) && checkWinnerPlayer2.contains(5) && checkWinnerPlayer2.contains(8)) {
            winner = 2
        }
        //Check Col 3
        if (checkWinnerPlayer1.contains(3) && checkWinnerPlayer1.contains(6) && checkWinnerPlayer1.contains(9)) {
            winner = 1
        }
        //Check Col 3
        if (checkWinnerPlayer2.contains(3) && checkWinnerPlayer2.contains(6) && checkWinnerPlayer2.contains(9)) {
            winner = 2
        }
        //Check Diagonal 1
        if (checkWinnerPlayer1.contains(1) && checkWinnerPlayer1.contains(5) && checkWinnerPlayer1.contains(9)) {
            winner = 1
        }
        //Check Diagonal 1
        if (checkWinnerPlayer2.contains(1) && checkWinnerPlayer2.contains(5) && checkWinnerPlayer2.contains(9)) {
            winner = 2
        }
        //Check Diagonal 2
        if (checkWinnerPlayer1.contains(3) && checkWinnerPlayer1.contains(5) && checkWinnerPlayer1.contains(7)) {
            winner = 1
        }
        //Check Diagonal 2
        if (checkWinnerPlayer2.contains(3) && checkWinnerPlayer2.contains(5) && checkWinnerPlayer2.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            resetList()
        }
    }



    fun addWinner(request: HistoryRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            _addWinner.postValue((ResourceState.loading()))
            val response = repository.addWinner(request)
            if(response.isSuccessful) {
                response.body().let {
//                    val addWinnerResponse = it!!
                    _addWinner.postValue(ResourceState.success(true))
                }
            }
            else {
                _addWinner.postValue(ResourceState.failed("Error to add winner"))
            }

        }

    }

    fun resetList() {
        checkWinnerPlayer1.clear()
        checkWinnerPlayer2.clear()
    }

}