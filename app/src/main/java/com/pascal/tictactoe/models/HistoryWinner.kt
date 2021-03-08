package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName

data class HistoryWinner(
    @SerializedName("id")
    val id: Int,

    @SerializedName("player1Name")
    val player1Name: String,

    @SerializedName("player2Name")
    val player2Name: String,

    @SerializedName("winnerName")
    val winnerName:String
) {
}