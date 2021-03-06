package com.pascal.tictactoe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.tictactoe.R
import com.pascal.tictactoe.holder.HistoryViewHolder
import com.pascal.tictactoe.models.HistoryResponse

class HistoryViewAdapter() : RecyclerView.Adapter<HistoryViewHolder>() {
   var listPlayer = ArrayList<HistoryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val historyView = inflater.inflate(R.layout.history_layout_recycler_view, parent, false)
        return HistoryViewHolder(historyView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val player = listPlayer[position]
        val number = position + 1
        holder.bind(player)
    }

        override fun getItemCount(): Int {
            return listPlayer.size
        }

        fun setItemList(list: List<HistoryResponse>) {
            listPlayer.addAll(list)
            notifyDataSetChanged()

        }
    }
