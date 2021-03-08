package com.pascal.tictactoe.history

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pascal.tictactoe.databinding.HistoryLayoutRecyclerViewBinding
import com.pascal.tictactoe.models.HistoryResponse
import com.pascal.tictactoe.models.HistoryWinner

class HistoryViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    private val binding = HistoryLayoutRecyclerViewBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(response: HistoryWinner) {
        binding.apply {
            tvPlayerName.text = "player1: ${response.player1Name}, player2: ${response.player2Name}"
            tvWinnerName.text = response.winnerName

        }
    }

}