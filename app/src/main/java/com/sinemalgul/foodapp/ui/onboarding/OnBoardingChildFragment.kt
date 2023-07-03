package com.sinemalgul.foodapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentOnboardingChildBinding

class OnBoardingChildFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingChildBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnboardingChildBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.ivOnBoarding.setImageResource(it.getInt(IMAGE_PATH, R.drawable.first))
            binding.tvTitle.text = it.getString(TITLE)
            binding.tvDesc.text = it.getString(DESC)
        }
    }

    companion object {

        private const val IMAGE_PATH = "IMAGE_PATH"
        private const val TITLE = "TITLE"
        private const val DESC = "DESC"

        @JvmStatic
        fun newInstance(animPath: Int, title: String, desc: String) =
            OnBoardingChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE_PATH, animPath)
                    putString(TITLE, title)
                    putString(DESC, desc)
                }
            }
    }
}