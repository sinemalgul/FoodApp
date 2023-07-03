package com.sinemalgul.foodapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.data.model.User
import com.sinemalgul.foodapp.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initViews() = with(binding) {
        button.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()
            val pass = editTextPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && phoneNumber.isNotEmpty()) {
                viewModel.signUp(User(email, name, phoneNumber), pass)
                findNavController().navigate(R.id.signUpToSignIn)
                Toast.makeText(requireContext(),
                    "Sign Up Successful!",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),
                    "Empty Fields Are Not Allowed !",
                    Toast.LENGTH_SHORT).show()
            }
        }

        textLogin.setOnClickListener {
            findNavController().navigate(R.id.signUpToSignIn)
        }
    }

    private fun initObservers() {
        viewModel.isSignUp.observe(viewLifecycleOwner) {
            if (it.first) {
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.signUpToSignIn)
            } else {
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }
}