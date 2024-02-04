package com.anirudh.easyfood.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anirudh.easyfood.R
import com.anirudh.easyfood.databinding.ActivityMealBinding
import com.anirudh.easyfood.databinding.PopularItemBinding
import com.anirudh.easyfood.db.MealDb
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.ui.fragments.HomeFragment
import com.anirudh.easyfood.viewModel.HomeViewModel
import com.anirudh.easyfood.viewModel.MealViewModel
import com.anirudh.easyfood.viewModel.ViewModelProviderFactory
import com.bumptech.glide.Glide

class MealActivity : AppCompatActivity() {

    lateinit var mealId: String
    lateinit var mealName: String
    lateinit var mealThumb: String
    lateinit var binding: ActivityMealBinding
    private lateinit var mealVm: MealViewModel
    private var meal: Meal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mealDb = MealDb.getInstance(this)
        val viewModelProviderFactory = ViewModelProviderFactory(mealDb)
        mealVm = ViewModelProvider(this, viewModelProviderFactory)[MealViewModel::class.java]
        updateMealInfo()
        updateViews()
        mealVm.getMealDetail(mealId)
        setupObservers()
    }

    private fun setupObservers() {
        mealVm.observeMealDetailLiveData().observe(this) {
            meal = it
            binding.tvMealCategory.text = it.strCategory
            binding.tvArea.text = it.strArea
            binding.tvDesc.text = it.strInstructions
        }
    }

    private fun updateViews() {
        Glide.with(applicationContext).load(mealThumb).into(
            binding.ivMealDetail
        )
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.btnAddFavorite.setOnClickListener {
            meal?.let { it1 ->
                mealVm.insertMeal(it1)
                Toast.makeText(
                    applicationContext,
                    "Saved meal to Fav ${it1.strMeal}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun updateMealInfo() {
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID) ?: ""
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME) ?: ""
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_PIC).toString()

    }
}