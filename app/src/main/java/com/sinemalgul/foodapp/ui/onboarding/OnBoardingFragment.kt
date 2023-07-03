package com.sinemalgul.foodapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.databinding.FragmentOnboardingBinding

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        dotsIndicator()
        val adapter = ViewPagerAdapter(requireActivity())
        binding.vpOnBoarding.adapter = adapter
        binding.vpOnBoarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.backbtn.visibility = View.GONE
                        binding.imageBack.visibility = View.GONE
                        binding.skipbtn.setOnClickListener {
                            findNavController().navigate(R.id.onBoardingToSignInAndSignUp)
                        }
                        binding.nextbtn.setOnClickListener {
                            binding.vpOnBoarding.currentItem = +1
                        }
                    }
                    1 -> {
                        binding.backbtn.visibility = View.VISIBLE
                        binding.imageBack.visibility = View.VISIBLE
                        binding.nextbtn.text = "NEXT"
                        binding.skipbtn.setOnClickListener {
                            findNavController().navigate(R.id.onBoardingToSignInAndSignUp)
                        }
                        binding.backbtn.setOnClickListener {
                            binding.vpOnBoarding.currentItem = -1
                        }
                        binding.nextbtn.setOnClickListener {
                            binding.vpOnBoarding.currentItem = binding.vpOnBoarding.currentItem + 1
                        }
                    }
                    else -> {
                        binding.backbtn.visibility = View.VISIBLE
                        binding.imageBack.visibility = View.VISIBLE
                        binding.nextbtn.text = "FINISH"
                        binding.skipbtn.setOnClickListener {
                            findNavController().navigate(R.id.onBoardingToSignInAndSignUp)
                        }
                        binding.nextbtn.setOnClickListener {
                            findNavController().navigate(R.id.onBoardingToSignInAndSignUp)
                        }
                        binding.backbtn.setOnClickListener {
                            binding.vpOnBoarding.currentItem = binding.vpOnBoarding.currentItem - 1
                        }
                    }
                }
            }
        })
    }

    private fun dotsIndicator() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.vpOnBoarding.adapter = adapter
        binding.dotsIndicator.attachTo(binding.vpOnBoarding)
    }
}