package com.pascal.tictactoe.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.api.HistoryApi
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.utils.Constans
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HistoryViewModel : ViewModel() {

   private var _historyWinner = MutableLiveData<List<HistoryResponse>>()
    val historyWinner: LiveData<List<HistoryResponse>>
    get() {
        return _historyWinner
    }

    fun getHistoryWinner() {
        val retrofit = Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HistoryApi::class.java)
        val call = service.getHistoryWin()
        call.enqueue(object : Callback<List<HistoryResponse>> {
            override fun onFailure(call: Call<List<HistoryResponse>>, t: Throwable) {
                Log.d("Error: " , t.toString())
            }

            override fun onResponse(call: Call<List<HistoryResponse>>, response: Response<List<HistoryResponse>>) {
                _historyWinner.value = response.body()
            }


        })
    }

}