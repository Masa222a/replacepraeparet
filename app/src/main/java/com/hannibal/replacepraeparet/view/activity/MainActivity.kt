package com.hannibal.replacepraeparet.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavMenu()
    }

    private fun setupBottomNavMenu() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHost) as NavHostFragment
        val navController = host.navController
        val bottomNav = binding.bottomNavigation
        bottomNav.setupWithNavController(navController)
    }

}