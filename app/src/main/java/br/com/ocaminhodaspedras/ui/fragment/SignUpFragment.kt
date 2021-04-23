package br.com.ocaminhodaspedras.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.databinding.FragmentLoginBinding
import br.com.ocaminhodaspedras.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding

    fun goToLogin(view: View){
        findNavController().navigate(R.id.action_signUpFragment_to_LoginFragment)
    }

    fun goToSignUp2(view: View){
        findNavController().navigate(R.id.action_signUpFragment_to_signUp2Fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.etEmailSignUp

        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }


}