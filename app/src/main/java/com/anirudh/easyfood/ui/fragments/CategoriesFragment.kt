package com.anirudh.easyfood.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.anirudh.easyfood.R
import com.anirudh.easyfood.adapters.CategoriesAdapter
import com.anirudh.easyfood.databinding.CategoryItemBinding
import com.anirudh.easyfood.databinding.FragmentCategoriesBinding
import com.anirudh.easyfood.databinding.FragmentFavoritesBinding
import com.anirudh.easyfood.pojo.MealCategory
import com.anirudh.easyfood.ui.activities.CategoryMealsActivity
import com.anirudh.easyfood.ui.activities.MainActivity
import com.anirudh.easyfood.viewModel.HomeViewModel


class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.observeCategoryListLiveData().observe(viewLifecycleOwner) {
            categoryAdapter.setMealCategories(it as ArrayList<MealCategory>)
        }
    }

    private fun setupRv() {
        categoryAdapter = CategoriesAdapter {
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, it.strCategory)
            intent.putExtra(HomeFragment.MEAL_NAME, it.strCategory)
            intent.putExtra(HomeFragment.MEAL_PIC, it.strCategoryDescription)
            startActivity(intent)
        }
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

}