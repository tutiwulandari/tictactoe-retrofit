package com.pascal.tictactoe.game

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.tictactoe.R
import com.pascal.tictactoe.databinding.FragmentBoardBinding
import com.pascal.tictactoe.home.RegistrationViewModel
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.utils.ResourceStatus
import com.pascal.tictactoe.models.HistoryWinner
import com.pascal.tictactoe.utils.PLAYER1
import com.pascal.tictactoe.utils.PLAYER2
import com.pascal.tictactoe.views.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment() : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var viewModel: BoardViewModel
    private lateinit var sharedViewModel: RegistrationViewModel
    private var activePlayer = ""
    private var player1: String = ""
    private var player2: String = ""
    private lateinit var navController: NavController
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        loadingDialog = LoadingDialog.build(requireContext())
        subscribe()
        arguments?.let {
            player1 = it.getString(PLAYER1).toString()
            player2 = it.getString(PLAYER2).toString()
            activePlayer = player1
            println("Player $player1 $player2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog.build(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPlayerTurn(activePlayer)

        navController = Navigation.findNavController(view)

        binding.apply {
            button00.setOnClickListener {
                playGame(1, it as Button)
            }
            button01.setOnClickListener {
                playGame(2, it as Button)

            }
            button02.setOnClickListener {
                playGame(3, it as Button)

            }
            button10.setOnClickListener {
                playGame(4, it as Button)
            }
            button11.setOnClickListener {
                playGame(5, it as Button)
            }
            button12.setOnClickListener {
                playGame(6, it as Button)
            }
            button20.setOnClickListener {
                playGame(7, it as Button)
            }
            button21.setOnClickListener {
                playGame(8, it as Button)
            }
            button22.setOnClickListener {
                playGame(9, it as Button)
            }

            historyButton.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_boardFragment_to_historyFragment)
            }

        }
    }


    private fun playGame(cellID: Int, buttonSelected: Button) {
        if (activePlayer == player1) {
            println("playgame: $player1, $player2")
            buttonSelected.text = "X"
            buttonSelected.setBackgroundColor(Color.parseColor("red"))
            buttonSelected.setTextColor(Color.parseColor("white"))
            viewModel.checkWinnerPlayer1.add(cellID)
        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundColor(Color.parseColor("green"))
            buttonSelected.setTextColor(Color.parseColor("white"))
            viewModel.checkWinnerPlayer2.add(cellID)
        }
        checkWinner()
        switchPlayer()
        showPlayerTurn(activePlayer)
        buttonSelected.isEnabled = false
    }

    private fun checkWinner() {
        viewModel.checkWinnerViewModel()
        if (viewModel.winner != -1) {
            if (viewModel.winner == 1) {
                val dataWinner = HistoryRequest(player1, player2, player1)
                viewModel.addWinner(dataWinner)
//                makeAllButtonEnabled()
            } else {
                val dataWinner = HistoryRequest(player1, player2, player2)
                viewModel.addWinner(dataWinner)
//                makeAllButtonEnabled()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetList()
    }

    private fun showPlayerTurn(player: String) {
        binding.playerturn.text = "$player Turn"
    }

    private fun makeAllButtonDisabled() {
        binding.apply {
            button00.isEnabled = false
            button01.isEnabled = false
            button02.isEnabled = false
            button10.isEnabled = false
            button11.isEnabled = false
            button12.isEnabled = false
            button21.isEnabled = false
            button22.isEnabled = false
            button20.isEnabled = false
        }
        resetButtonText()
    }

    private fun makeAllButtonEnabled() {
        binding.apply {
            button00.isEnabled = false
            button01.isEnabled = false
            button02.isEnabled = false
            button10.isEnabled = false
            button11.isEnabled = false
            button12.isEnabled = false
            button21.isEnabled = false
            button22.isEnabled = false
            button20.isEnabled = false
        }
//        Toast.makeText(requireContext(), "Silahkan Bermain Lagi", Toast.LENGTH_LONG).show()

    }

    private fun resetButtonText() {
        binding.apply {
            button00.text = ""
            button01.text = ""
            button02.text = ""
            button10.text = ""
            button11.text = ""
            button12.text = ""
            button21.text = ""
            button22.text = ""
            button20.text = ""
        }
    }


    private fun switchPlayer() {
        if (activePlayer == player1) {
            activePlayer = player2
        } else {
            activePlayer = player1
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(BoardViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.addWinner.observe(this, Observer {
            when (it.status) {
                ResourceStatus.LOADING -> {
                    loadingDialog.show()
                    viewModel.winner = -1
                    makeAllButtonDisabled()
                }
                ResourceStatus.SUCCESS -> {
                    loadingDialog.hide()
                    makeAllButtonEnabled()
                    val dataState = it?.data as HistoryWinner
                    Toast.makeText(requireContext(), "${dataState.winnerName} WIN" , Toast.LENGTH_LONG).show()
                    activePlayer = player2
                }
                ResourceStatus.FAILURE -> {
                    loadingDialog.hide()
                makeAllButtonEnabled()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}