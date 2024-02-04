package com.anirudh.easyfood.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anirudh.easyfood.db.MealDb

class ViewModelProviderFactory(val mealDb: MealDb? = null) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            mealDb?.let{ HomeViewModel(it) } as T
        } else if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            MealViewModel(mealDb) as T
        } else if (modelClass.isAssignableFrom(CategoryMealsViewModel::class.java)) {
            CategoryMealsViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}