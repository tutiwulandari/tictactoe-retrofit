package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName


data class HistoryRequest (
    @SerializedName("player1Name")
    val player1Name : String,

    @SerializedName("player2Name")
    val player2Name : String,

    @SerializedName("winnerName")
    val winnerName : String
) {

}