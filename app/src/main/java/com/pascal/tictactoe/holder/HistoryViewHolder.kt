package com.pascal.tictactoe.holder

import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.pascal.tictactoe.databinding.HistoryLayoutRecyclerViewBinding
import com.pascal.tictactoe.models.HistoryResponse

class HistoryViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    private val binding = HistoryLayoutRecyclerViewBinding.bind(view)

    fun bind(response: HistoryResponse) {
        binding.apply {
            tvPlayerName.text = "player1: ${response.player1}, player2: ${response.player2}"
            tvWinnerName.text = "${response.winName}"

        }
    }

}