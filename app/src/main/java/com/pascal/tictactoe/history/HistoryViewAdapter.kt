package com.pascal.tictactoe.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.tictactoe.R
import com.pascal.tictactoe.models.HistoryWinner

class HistoryViewAdapter() : RecyclerView.Adapter<HistoryViewHolder>() {
   var data: List<HistoryWinner> = ArrayList<HistoryWinner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val historyView = inflater.inflate(R.layout.history_layout_recycler_view, parent, false)
        return HistoryViewHolder(historyView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

        override fun getItemCount(): Int = data.size

        fun setDataList(list: List<HistoryWinner>) {
            this.data = list
            notifyDataSetChanged()

        }
    }
