package br.com.ocaminhodaspedras.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    fun goToSignUp(view: View) {
        findNavController().navigate(R.id.action_LoginFragment_to_signUpFragment)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }




}