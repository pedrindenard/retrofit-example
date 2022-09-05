package com.app.myapplication.feature.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.myapplication.R
import com.app.myapplication.databinding.ActivityMainBinding
import com.app.myapplication.util.Animation

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavController()
    }

    private fun setNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_controller) as NavHostFragment
        val navController = navHostFragment.navController
        setNavMenu(navController)
        setListener(navController)
    }

    private fun setNavMenu(navController: NavController) {
        binding.mainBottomNav.setupWithNavController(navController)
        binding.mainBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main_home -> {
                    navController.navigate(R.id.home_fragment, null, Animation.animFromUpToDown)
                }
                R.id.main_favorite -> {
                    navController.navigate(R.id.favorite_fragment, null, Animation.animFromUpToDown)
                }
                R.id.main_search -> {
                    navController.navigate(R.id.search_fragment, null, Animation.animFromUpToDown)
                }
            }
            true
        }
    }

    private fun setListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.mainBottomNav.visibility = if (
                destination.id == R.id.home_fragment ||
                destination.id == R.id.favorite_fragment ||
                destination.id == R.id.search_fragment
            ) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}