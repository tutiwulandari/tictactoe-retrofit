package com.pascal.tictactoe.history

import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pascal.tictactoe.databinding.FragmentHistoryBinding
import com.pascal.tictactoe.repositories.HistoryRepositoryImpl
import com.pascal.tictactoe.views.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyAdapter : HistoryViewAdapter
    private lateinit var loadingDialog :AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog.build(requireContext())
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private fun subscribe() {
        historyViewModel.historyLiveData.observe(this, Observer {
            historyAdapter.setDataList(it)

        })
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
            historyViewModel.getHistoryWinner()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}
