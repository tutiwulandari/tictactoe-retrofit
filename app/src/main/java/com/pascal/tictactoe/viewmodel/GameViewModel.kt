package com.pascal.tictactoe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.api.HistoryApi
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.utils.Constans
import com.pascal.tictactoe.utils.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameViewModel : ViewModel() {
    private var _addWinner = MutableLiveData<HistoryResponse>()
    val addWinner : LiveData<HistoryResponse>
    get() {
        return _addWinner
    }

    var winner = -1
    var checkWinnerPlayer1 = ArrayList<Int>()
    var checkWinnerPlayer2 = ArrayList<Int>()

    private var _validationPLayer = MutableLiveData<ResourceState>()
    val validationPlayer: LiveData<ResourceState>
        get() {
            return _validationPLayer
        }

    fun inputValidationPlayer(player1: String, player2: String) {
        GlobalScope.launch {
            _validationPLayer.postValue(ResourceState.loading())
            delay(2000)
            if (player1.isNullOrBlank() || player2.isNullOrBlank()) {
                _validationPLayer.postValue(ResourceState.failed("Name Player Must Not Blank or Empty"))
            } else {
                _validationPLayer.postValue(ResourceState.success(true))
            }
        }
    }

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

    fun resetList() {
        checkWinnerPlayer1.clear()
        checkWinnerPlayer2.clear()
    }



    fun addHistoryWinner(request: HistoryRequest) {
        val retrofit = Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HistoryApi::class.java)
        val call = service.addWinner(request)
       call.enqueue(object : Callback<HistoryResponse> {
           override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.d("Error: " , t.toString())
           }

           override fun onResponse(call: Call<HistoryResponse>, response: Response<HistoryResponse>) {
              _addWinner.value = response.body()
           }

       })
    }
}