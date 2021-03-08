package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName

data class ResponseHistory(
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: HistoryWinner? = null,

    @field:SerializedName("message")
    val message: Any? = null
) {
}