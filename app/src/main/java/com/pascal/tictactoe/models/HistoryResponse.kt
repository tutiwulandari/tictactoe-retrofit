package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName


data class HistoryResponse (

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: HistoryWinnerList? = null,

    @field:SerializedName("message")
    val message: Any? = null
) {

}