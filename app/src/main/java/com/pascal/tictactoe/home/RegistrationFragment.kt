package com.pascal.tictactoe.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.pascal.tictactoe.R
import com.pascal.tictactoe.databinding.FragmentRegistrationBinding
import com.pascal.tictactoe.utils.ResourceStatus
import com.pascal.tictactoe.game.BoardViewModel
import com.pascal.tictactoe.views.LoadingDialog

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
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
                registrationViewModel.setPLayer1(player1)
                registrationViewModel.setPLayer2(player2)
                registrationViewModel.inputValidationPlayer(player1,player2)

            }
        }
    }

    private fun initViewModel() {
        registrationViewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)
    }

    private fun subscribe() {
        registrationViewModel.validationPlayer.observe(this, Observer {
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
                    findNavController().navigate(R.id.action_registrationFragment_to_boardFragment)
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }
}