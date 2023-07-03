package com.sinemalgul.foodapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sinemalgul.foodapp.MainActivity
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentProfileBinding
import com.sinemalgul.foodapp.ui.chatbot.MessagingAdapter
import com.sinemalgul.foodapp.ui.home.RestaurantsAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel by lazy { ProfileViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        binding.btnSignOut.setOnClickListener {
            profileViewModel.signOut()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.chatBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_messageFragment)
        }

    }

    private fun initObservers() = with(binding) {
        profileViewModel.user.observe(viewLifecycleOwner) {
            tvEmail.text = it.email
            tvName.text = it.name
            tvPhone.text = it.phoneNumber
        }
    }
}