package br.com.ocaminhodaspedras.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.databinding.FragmentLoginBinding
import br.com.ocaminhodaspedras.domain.LoginResult
import br.com.ocaminhodaspedras.ui.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var auth: FirebaseAuth


    private lateinit var binding: FragmentLoginBinding
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewmodel = viewmodel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.result.observe(viewLifecycleOwner) {
            when (it) {
                is LoginResult.Success -> {
                    requireActivity().finish()
                    findNavController().navigate(R.id.action_LoginFragment_to_mainActivity)
                }
                is LoginResult.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun login(v: View) {
        viewmodel.login()
    }

    @Suppress("UNUSED_PARAMETER")
    fun goForgot(v: View) {
        Toast.makeText(context, "YET TO IMPLEMENT FORGOT CICLE", Toast.LENGTH_LONG).show()
//        findNavController().navigate(R.id.action)
    }

    @Suppress("UNUSED_PARAMETER")
    fun goSignUp(v: View) {
        findNavController().navigate(R.id.action_LoginFragment_to_signUpFragment)
    }

}



