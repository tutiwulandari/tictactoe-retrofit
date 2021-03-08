package com.pascal.tictactoe.models

import com.google.gson.annotations.SerializedName

data class HistoryWinnerList(
    @SerializedName("list")
    val historyList : List<HistoryWinner>
) {
}