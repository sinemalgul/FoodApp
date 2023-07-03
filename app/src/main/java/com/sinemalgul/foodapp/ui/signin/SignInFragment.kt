package com.sinemalgul.foodapp.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.btnhome.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.signIn(email, pass)
            } else {
                Toast.makeText(requireContext(),
                    "Empty Fields Are Not Allowed !",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.textSignUp.setOnClickListener {
            findNavController().navigate(R.id.signInToSignUp)
        }
    }

    private fun initObservers() {
        viewModel.isSignIn.observe(viewLifecycleOwner) {
            if (it.first) {
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.signInToHome)
            } else {
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }
}