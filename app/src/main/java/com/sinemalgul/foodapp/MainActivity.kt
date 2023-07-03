package com.sinemalgul.foodapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.sinemalgul.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        setupWithNavController(binding.bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.splashFragment, R.id.onboardingFragment, R.id.signInAndSignupFragment,
                R.id.signInFragment, R.id.signUpFragment, R.id.paymentFragment, R.id.messageFragment-> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}