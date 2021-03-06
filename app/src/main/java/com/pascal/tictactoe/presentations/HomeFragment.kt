package com.pascal.tictactoe.presentations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.pascal.tictactoe.R
import com.pascal.tictactoe.databinding.FragmentHomeBinding
import com.pascal.tictactoe.utils.ResourceStatus
import com.pascal.tictactoe.viewmodel.GameViewModel
import com.pascal.tictactoe.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment() : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var registrationViewModel : RegistrationViewModel
    private lateinit var loadingDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog.build(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            startbutton.setOnClickListener {
                val player1 = inputplayer1.text.toString()
                val player2 = inputplayer2.text.toString()
                gameViewModel.inputValidationPlayer(player1, player2)
                registrationViewModel.setPLayer1(player1)
                registrationViewModel.setPLayer2(player2)
            }
        }
    }

    fun initViewModel() {
        gameViewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        registrationViewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)
    }

    fun subscribe() {
        gameViewModel.validationPlayer.observe(this) {
            when (it.status) {
                ResourceStatus.LOADING -> {
                    loadingDialog.show()
                }
                ResourceStatus.FAILURE -> {
                    loadingDialog.hide()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                ResourceStatus.SUCCESS -> {
                    loadingDialog.hide()
                    view?.findNavController()?.navigate(R.id.action_homeFragment_to_screenGame)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}