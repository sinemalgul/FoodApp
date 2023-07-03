package com.sinemalgul.foodapp.ui.signinandsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentSignInAndSignUpBinding

class SignInAndSignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignInAndSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInAndSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.signInAndSignUpToSignIn)
        }
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.signInAndSignUpToSignUp)
        }
    }
}