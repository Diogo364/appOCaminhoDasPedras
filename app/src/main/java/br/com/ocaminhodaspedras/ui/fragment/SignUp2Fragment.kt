package br.com.ocaminhodaspedras.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.databinding.FragmentLoginBinding
import br.com.ocaminhodaspedras.databinding.FragmentSignUp2Binding

class SignUp2Fragment : Fragment() {

    private lateinit var binding : FragmentSignUp2Binding

    fun goToLogin(view: View) {
        findNavController().navigate(R.id.action_signUp2Fragment_to_LoginFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUp2Binding.inflate(inflater, container, false)

        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }


}