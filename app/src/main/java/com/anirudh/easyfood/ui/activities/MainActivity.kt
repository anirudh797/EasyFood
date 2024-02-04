package com.anirudh.easyfood.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.anirudh.easyfood.R
import com.anirudh.easyfood.db.MealDb
import com.anirudh.easyfood.viewModel.HomeViewModel
import com.anirudh.easyfood.viewModel.ViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by lazy {
        val mealDb = MealDb.getInstance(this)
        val viewModelProviderFactory = ViewModelProviderFactory(mealDb)
        ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)

        NavigationUI.setupWithNavController(bottomNav, navController)
    }
}