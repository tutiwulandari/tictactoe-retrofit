package com.pascal.tictactoe.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.pascal.tictactoe.R
import com.pascal.tictactoe.databinding.FragmentRegistrationBinding
import com.pascal.tictactoe.utils.ResourceStatus
import com.pascal.tictactoe.game.BoardViewModel
import com.pascal.tictactoe.utils.PLAYER1
import com.pascal.tictactoe.utils.PLAYER2
import com.pascal.tictactoe.views.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel : RegistrationViewModel
    private lateinit var loadingDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog.build(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            startbutton.setOnClickListener {
                val player1 = inputplayer1.text.toString()
                val player2 = inputplayer2.text.toString()
//                viewModel.setPLayer1(player1)
//                viewModel.setPLayer2(player2)
                println("Registration: $player1 $player2")
                viewModel.inputValidationPlayer(player1,player2)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.validationPlayer.observe(this, Observer {
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
                    val bundle = bundleOf(
                        PLAYER1 to binding.inputplayer1.text.toString(),
                        PLAYER2 to binding.inputplayer2.text.toString()

                    )
                    Navigation.findNavController(requireView()).navigate(R.id.action_registrationFragment_to_boardFragment, bundle)
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }
}