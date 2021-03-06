package com.pascal.tictactoe.presentations

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.tictactoe.R
import com.pascal.tictactoe.databinding.FragmentScreenGameBinding
import com.pascal.tictactoe.models.HistoryRequest
import com.pascal.tictactoe.viewmodel.GameViewModel
import com.pascal.tictactoe.viewmodel.HistoryViewModel
import com.pascal.tictactoe.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_screen_game.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ScreenGame() : Fragment() {

    private lateinit var binding: FragmentScreenGameBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var registrationViewModel : RegistrationViewModel
    private lateinit var historyViewModel: HistoryViewModel
    private var activePlayer = ""
    private lateinit var player1 :String
    private lateinit var player2 : String
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
        player1 = registrationViewModel.player1.value.toString()
        player2 = registrationViewModel.player2.value.toString()
        activePlayer = player1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenGameBinding.inflate(layoutInflater)
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
                navController.navigate(R.id.action_screenGame_to_historyFragment)
            }
//            button_exit.setOnClickListener {
//                navController.navigate(R.id.action_screenGame_to_homeFragment)
//            }
        }


    }


    companion object {
        @JvmStatic
        fun newInstance() = ScreenGame()
    }


    private fun playGame(cellID: Int, buttonSelected: Button)  {
        if (activePlayer == player1) {
            buttonSelected.text = "X"
            buttonSelected.setBackgroundColor(Color.parseColor("red"))
            buttonSelected.setTextColor(Color.parseColor("white"))
            gameViewModel.checkWinnerPlayer1.add(cellID)
        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundColor(Color.parseColor("green"))
            buttonSelected.setTextColor(Color.parseColor("white"))
            gameViewModel.checkWinnerPlayer2.add(cellID)
        }
        checkWinner()
        switchPlayer()
        showPlayerTurn(activePlayer)
        buttonSelected.isEnabled = false
    }

    private fun checkWinner() {
        gameViewModel.checkWinnerViewModel()
        if (gameViewModel.winner != -1) {
            if (gameViewModel.winner == 1) {
                makeAllButtonDisabled()
                gameViewModel.addHistoryWinner(HistoryRequest(player1, player2, player1))
                viewLifecycleOwner.lifecycleScope.launch{
                    delay(3000)
                    makeAllButtonEnabled()
                    gameViewModel.winner = -1
                }
                activePlayer = player2
            } else {
                makeAllButtonDisabled()
                gameViewModel.addHistoryWinner(HistoryRequest(player1, player2, player2))
                viewLifecycleOwner.lifecycleScope.launch{
                    delay(3000)
                    makeAllButtonEnabled()
                    gameViewModel.winner = -1
                }
                activePlayer = player2
            }
        }

    }

    override fun onPause() {
        super.onPause()
        Log.i("SCREEN GAME FRAGMENT", "ON PAUSE")
        gameViewModel.resetList()
    }




    private fun showPlayerTurn(player: String) {
        playerturn.text = player + " Turn"
    }

    private fun makeAllButtonDisabled() {
        button00.isEnabled = false
        button01.isEnabled = false
        button02.isEnabled = false
        button10.isEnabled = false
        button11.isEnabled = false
        button12.isEnabled = false
        button21.isEnabled = false
        button22.isEnabled = false
        button20.isEnabled = false
        resetButtonText()
    }

    private fun makeAllButtonEnabled() {
        button00.isEnabled = true
        button01.isEnabled = true
        button02.isEnabled = true
        button10.isEnabled = true
        button11.isEnabled = true
        button12.isEnabled = true
        button21.isEnabled = true
        button22.isEnabled = true
        button20.isEnabled = true
//        Toast.makeText(requireContext(), "Silahkan Bermain Lagi", Toast.LENGTH_LONG).show()

    }

    private fun resetButtonText() {
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


    private fun switchPlayer() {
        if (activePlayer == player1) {
            activePlayer = player2
        } else {
            activePlayer = player1
        }
    }

    private fun initViewModel() {
        gameViewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        registrationViewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)
        historyViewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
    }

    private fun subscribe(){
        gameViewModel.addWinner.observe(this) {
            Toast.makeText(requireContext(), "${it.winName.toUpperCase()} WIN", Toast.LENGTH_LONG).show()
        }
    }
}