package com.example.shopping_list_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping_list_app.R

import androidx.navigation.NavController
import androidx.navigation.findNavController


import androidx.navigation.ui.AppBarConfiguration


import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shopping_list_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.itemListFragment,
                R.id.historyFragment
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
