package com.anirudh.easyfood.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anirudh.easyfood.adapters.MostPopularAdapter
import com.anirudh.easyfood.databinding.FragmentHomeBinding
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.pojo.PopularMeal
import com.anirudh.easyfood.ui.activities.MealActivity

import com.anirudh.easyfood.viewModel.HomeViewModel
import com.anirudh.easyfood.viewModel.ViewModelProviderFactory
import com.bumptech.glide.Glide


class HomeFragment : Fragment() {

    companion object{
        const val MEAL_ID = "meal_id"
        const val MEAL_PIC = "mealImage"
        const val MEAL_NAME = "mealName"
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var popularMealAdapter: MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModelProviderFactory = ViewModelProviderFactory()
        homeViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        onRandomMealClick()
        setupAdapter()
        homeViewModel.getRandomMeal()
        homeViewModel.getPopularItems()
    }

    private fun setupAdapter() {
        popularMealAdapter = MostPopularAdapter {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,it.idMeal)
            intent.putExtra(MEAL_NAME,it.strMeal)
            intent.putExtra(MEAL_PIC,it.strMealThumb)
            startActivity(intent)
        }
        binding.rv.apply {
            adapter = popularMealAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun onRandomMealClick() {
        binding.ivRandomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            val meal = homeViewModel.randomMealLiveData.value
            intent.putExtra(MEAL_ID,meal?.idMeal)
            intent.putExtra(MEAL_NAME,meal?.strMeal)
            intent.putExtra(MEAL_PIC,meal?.strMealThumb)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        homeViewModel.observeRandomMealLiveData().observe(
            viewLifecycleOwner
        ) {
            Glide.with(this).load(it.strMealThumb).into(binding.ivRandomMeal)
        }
        homeViewModel.observePopularMealsLiveData().observe(
            viewLifecycleOwner
        ) {
            popularMealAdapter.setMeals(it as? ArrayList<PopularMeal> ?: arrayListOf())
        }

    }

}