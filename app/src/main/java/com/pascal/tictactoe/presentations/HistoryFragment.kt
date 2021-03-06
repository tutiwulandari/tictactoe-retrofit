package com.pascal.tictactoe.presentations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pascal.tictactoe.R
import com.pascal.tictactoe.adapter.HistoryViewAdapter
import com.pascal.tictactoe.databinding.FragmentHistoryBinding
import com.pascal.tictactoe.viewmodel.HistoryViewModel


class HistoryFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyAdapter : HistoryViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        historyViewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
    }

    private fun subscribe() {
        historyViewModel.historyWinner.observe(requireActivity()) {
            historyAdapter.setItemList(it)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        historyViewModel.getHistoryWinner()
        binding.apply {
            historyAdapter = HistoryViewAdapter()
            winnerRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = historyAdapter
            }
            backToScreenGameButon.setOnClickListener {
                view?.findNavController()?.popBackStack()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}
