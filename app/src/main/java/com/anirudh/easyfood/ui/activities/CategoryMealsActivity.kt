package com.anirudh.easyfood.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.anirudh.easyfood.adapters.CategoryMealsAdapter

import com.anirudh.easyfood.databinding.AcitivityCategoryBinding
import com.anirudh.easyfood.pojo.MealsByCategory
import com.anirudh.easyfood.ui.fragments.HomeFragment
import com.anirudh.easyfood.viewModel.CategoryMealsViewModel
import com.anirudh.easyfood.viewModel.ViewModelProviderFactory

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding: AcitivityCategoryBinding
    lateinit var categoryMealsViewModel: CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        var viewModelProviderFactory = ViewModelProviderFactory()
        categoryMealsViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[CategoryMealsViewModel::class.java]

        intent.getStringExtra(HomeFragment.CATEGORY_NAME)
            ?.let { categoryMealsViewModel.getMealsByCategory(it) }
        categoryMealsViewModel.observeMealsLiveData().observe(this) {
            binding.tvCategoryCount.text = it.size.toString()
            categoryMealsAdapter.setMeals(it as ArrayList<MealsByCategory>)
        }
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter() {
            val intent = Intent(applicationContext, MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID, it.idMeal)
            intent.putExtra(HomeFragment.MEAL_PIC, it.strMealThumb)
            intent.putExtra(HomeFragment.MEAL_NAME, it.strMeal)
            startActivity(intent)
        }
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categoryMealsAdapter
        }
    }

}