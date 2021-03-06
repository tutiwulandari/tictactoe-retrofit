package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("player1Name")
    val player1: String,

    @SerializedName("player2Name")
    val player2: String,

    @SerializedName("winnerName")
    val winName: String

)