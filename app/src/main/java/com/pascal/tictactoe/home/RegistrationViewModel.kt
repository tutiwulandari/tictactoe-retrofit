package com.pascal.tictactoe.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.tictactoe.utils.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    private var _validationPLayer = MutableLiveData<ResourceState>()
    val validationPlayer: LiveData<ResourceState>
        get() {
            return _validationPLayer
        }

    fun inputValidationPlayer(p1: String, p2: String) {
        GlobalScope.launch {
            _validationPLayer.postValue(ResourceState.loading())
            delay(2000)
            if (p1.isNullOrBlank() || p2.isNullOrBlank()) {
                _validationPLayer.postValue(ResourceState.failed("Name Player Must Not Blank or Empty"))
            } else {
                _validationPLayer.postValue(ResourceState.success(true))
            }
        }
    }

}