package com.pascal.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel: ViewModel() {

    private var _player1 = MutableLiveData<String>()
    private var _player2 = MutableLiveData<String>()

    val player1 :LiveData<String>
        get() {
            return _player1
        }
    val player2 : LiveData<String>
        get() {
            return _player2
        }


    fun setPLayer1(player1 : String) {
        _player1.value = player1
    }

    fun setPLayer2(player2 : String) {
        _player2.value = player2
    }
}