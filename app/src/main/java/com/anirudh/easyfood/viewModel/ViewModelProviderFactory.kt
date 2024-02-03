package com.anirudh.easyfood.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel() as T
        } else if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            MealViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}